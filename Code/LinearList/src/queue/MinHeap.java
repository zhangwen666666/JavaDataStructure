package queue;

import linklist.ListNode;

public class MinHeap {
    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        array = new ListNode[capacity];
    }

    //添加元素
    public boolean offer(ListNode node) {
        if (isFull()) {
            return false;
        }
        int cur = size++;
        int parent = (cur - 1) / 2;
        while (cur != 0 && node.val < array[parent].val) {
            array[cur] = array[parent];
            cur = parent;
            parent = (cur - 1) / 2;
        }
        array[cur] = node;
        return true;
    }

    //删除元素
    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        ListNode node = array[0];
        array[0] = array[size - 1];
        array[size - 1] = null;
        size--;
        downAdjustmentHeap(0);
        return node;
    }

    private void downAdjustmentHeap(int parent) {
        if (2 * parent + 1 >= size)
            return;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        if (right < size) {
            if (array[left].val <= array[right].val && array[left].val <= array[parent].val) {
                swap(left, parent);
                downAdjustmentHeap(left);
                return;
            } else if (array[parent].val < array[left].val && array[parent].val < array[right].val) {
                return;
            } else {
                swap(right, parent);
                downAdjustmentHeap(right);
                return;
            }
        } else {
            if(array[left].val > array[parent].val)
                return;
            swap(left,parent);
            downAdjustmentHeap(left);
        }
    }

    private void swap(int index, int minIndex) {
        ListNode temp = array[index];
        array[index] = array[minIndex];
        array[minIndex] = temp;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //判断是否已满
    public boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.getList(new int[]{5, 3, 7, 1, 6, 2});
        ListNode cur = list;
        MinHeap minHeap = new MinHeap(6);
        while (cur != null) {
            minHeap.offer(cur);
            cur = cur.next;
        }
        System.out.println(minHeap.array);
        minHeap.poll();
        minHeap.poll();
        minHeap.poll();
    }
}

