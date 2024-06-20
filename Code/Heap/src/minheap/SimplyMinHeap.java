package minheap;

public class SimplyMinHeap {
    int[] array;
    int size;


    /**
     * 建立一个容量为capacity的空堆
     *
     * @param capacity 堆的容量
     */
    public SimplyMinHeap(int capacity) {
        array = new int[capacity];
    }


    /**
     * 根据数组array建堆
     *
     * @param array 数组
     */
    public SimplyMinHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    //建堆
    private void heapify() {
        //从最后一个非叶子节点开始向前到根节点
        //依次对每一个节点执行下潜
        for (int i = (size - 2) / 2; i >= 0; i--) {
            down(i);
        }
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {
        if(isEmpty())
            throw new RuntimeException("堆为空");
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 被删除的元素
     */
    public int poll() {
        if (isEmpty())
            throw new RuntimeException("堆为空");
        int e = array[0];
        array[0] = array[--size];
        down(0);
        return e;
    }

    /**
     * 删除指定所引处的元素
     *
     * @param index 索引
     * @return 被删除的元素
     */
    public int poll(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("索引非法");
        int e = array[index];
        if (index == size - 1) {
            size--;
            return e;
        }
        array[index] = array[--size];
        down(index);
        return e;
    }

    /**
     * 替换堆顶元素
     *
     * @param elem 新元素
     */
    public void replace(int elem) {
        array[0] = elem;
        down(0);
    }

    /**
     * 向堆的底部添加元素
     *
     * @param elem 待添加的元素
     * @return 是否添加成功
     */
    public boolean offer(int elem) {
        if (isFull())
            return false;
        up(elem, size++);
        return true;
    }

    /**
     * @return 堆是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return 堆是否已满
     */
    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return this.size;
    }

    /**
     * 将元素elem上浮，直至满足堆的特性
     *
     * @param elem  待上浮元素
     * @param index 待上浮元素的索引
     */
    private void up(int elem, int index) {
        int cur = index;
        while (cur > 0) {
            int parent = (cur - 1) / 2;
            if (elem < array[parent])
                array[cur] = array[parent];
            else {
                array[cur] = elem;
                return;
            }
            cur = parent;
        }
        if (cur == 0)
            array[cur] = elem;
    }

    /**
     * 将索引处的元素下潜
     *
     * @param parent 索引
     */
    private void down(int parent) {
        //只要当前元素还有子节点
        while (parent * 2 + 1 < size) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            int min = parent;
            if (array[left] < array[min])
                min = left;
            if (right < size && array[right] < array[min])
                min = right;
            if (min == parent)   //父节点元素最小
                return;
            swap(min, parent);
            parent = min;
        }
    }

    /**
     * 交换两个索引处的元素值
     *
     * @param i 索引1
     * @param j 索引2
     */
    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "[]";
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++) {
            s.append(array[i]).append(" ");
        }
        s.deleteCharAt(s.length() - 1);
        s.append("]");
        return s.toString();
    }

    public static void heapSorted(int[] array) {
        SimplyMinHeap heap = new SimplyMinHeap(array);
        while (heap.size > 1) {
            heap.swap(0, --heap.size);
            heap.down(0);
        }
    }
}
