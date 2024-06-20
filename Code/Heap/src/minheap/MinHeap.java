package minheap;

import java.util.Comparator;
import java.util.Objects;

public class MinHeap<E> {
    E[] array;
    int size;
    private final Comparator<? super E> comparator; //比较器对象

    public MinHeap() {
        this(1024, null);
    }

    /**
     * 建立一个容量为capacity的空堆
     *
     * @param capacity 堆的容量
     */

    public MinHeap(int capacity) {
        this(capacity, null);
    }

    public MinHeap(int capacity, Comparator<? super E> comparator) {
        array = (E[]) new Object[capacity];
        this.comparator = comparator;
    }


    /**
     * 根据数组array建堆
     *
     * @param array 数组
     */
    public MinHeap(E[] array) {
        this(array, null);
    }

    public MinHeap(E[] array, Comparator<? super E> comparator) {
        this.array = array;
        this.size = array.length;
        this.comparator = comparator;
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
    public E peek() {
        if (array == null)
            return null;
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 被删除的元素
     */
    public E poll() {
        if (isEmpty())
            return null;
        E e = array[0];
        array[0] = array[size - 1];
        array[--size] = null;
        down(0);
        return e;
    }

    /**
     * 删除指定所引处的元素
     *
     * @param index 索引
     * @return 被删除的元素
     */
    public E poll(int index) {
        if (index < 0 || index >= size)
            return null;
        E e = array[index];
        if (index == size - 1) {
            array[--size] = null;
            return e;
        }
        array[index] = array[size - 1];
        array[--size] = null;
        down(index);
        return e;
    }

    /**
     * 替换堆顶元素
     *
     * @param elem 新元素
     */
    public void replace(E elem) {
        array[0] = elem;
        down(0);
    }

    /**
     * 向堆的底部添加元素
     *
     * @param elem 待添加的元素
     * @return 是否添加成功
     */
    public boolean offer(E elem) {
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
    private void up(E elem, int index) {
        int cur = index;
        while (cur > 0) {
            int parent = (cur - 1) / 2;
            Comparator<? super E> cpr = comparator;
            if (cpr == null) {
                Objects.requireNonNull(elem);
                Comparable<? super E> e = (Comparable<? super E>) elem;
                if (e.compareTo(array[parent]) < 0)
                    array[cur] = array[parent];
                else {
                    array[cur] = elem;
                    return;
                }
            } else {
                if (cpr.compare(elem, array[parent]) < 0)
                    array[cur] = array[parent];
                else {
                    array[cur] = elem;
                    return;
                }
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
            Comparator<? super E> cpr = comparator;
            if (cpr == null) {
                Objects.requireNonNull(array[left]);
                Comparable<? super E> leftValue = (Comparable<? super E>) array[left];
                if (leftValue.compareTo(array[min]) < 0)
                    min = left;
                Objects.requireNonNull(array[min]);
                Comparable<? super E> minValue = (Comparable<? super E>) array[min];
                if (right < size && minValue.compareTo(array[right]) > 0)
                    min = right;
            } else {
                if (cpr.compare(array[left], array[min]) < 0)
                    min = left;
                if (right < size && cpr.compare(array[right], array[min]) < 0)
                    min = right;
            }
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
        E tmp = array[i];
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

    public static <T> void heapSorted(T[] array) {
        MinHeap<T> heap = new MinHeap<>(array);
        while (heap.size > 1) {
            heap.swap(0, --heap.size);
            heap.down(0);
        }
    }
}
