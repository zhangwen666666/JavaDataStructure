package minheap;

public class LeetCode703 {
    SimplyMinHeap heap;

    public LeetCode703(int k, int[] nums) {
        heap = new SimplyMinHeap(k);
        for (int i = 0; i < k && i < nums.length; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (heap.peek() < nums[i])
                heap.replace(nums[i]);
        }
    }

    public int add(int val) {
        if (!heap.isFull())
            heap.offer(val);
        else if (heap.peek() < val)
            heap.replace(val);
        return heap.peek();
    }

    private static class SimplyMinHeap {
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
         * 获取堆顶元素
         *
         * @return 堆顶元素
         */
        public int peek() {
            if (isEmpty())
                throw new RuntimeException("堆为空");
            return array[0];
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
            // 只要当前元素还有子节点
            while (parent * 2 + 1 < size) {
                int left = parent * 2 + 1;
                int right = parent * 2 + 2;
                int min = parent;
                if (array[left] < array[min])
                    min = left;
                if (right < size && array[right] < array[min])
                    min = right;
                if (min == parent) // 父节点元素最小
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
    }

    public static void main(String[] args) {
        int[] nums = {5, -1};
        LeetCode703 leetCode703 = new LeetCode703(3, nums);
        System.out.println(leetCode703.heap);
        int ret = leetCode703.add(2);
        System.out.println(ret);
    }
}
