package queue;

/**
 * 基于大顶堆（大顶推使用数组实现）来实现的优先级队列
 *
 * @param <E> 优先级队列所存储的元素类型
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull())
            return false;
        array[size++] = value;
        upAdjustmentHeap();
        return true;
    }

    /**
     * 将堆重新调整为大顶堆
     * 插入元素后的向上调整
     */
    private void upAdjustmentHeap() {
        int curIndex = size - 1;
        int parentIndex = (curIndex - 1) / 2;
        while (parentIndex >= 0) {
            if (array[curIndex].priority() > array[parentIndex].priority()) {
                swap(curIndex, parentIndex);
                curIndex = parentIndex;
                parentIndex = (curIndex - 1) / 2;
            } else {
                return;
            }
        }
    }

    //交换下标为i和j的元素值
    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        E e = (E) array[0];
        array[0] = array[size - 1]; //将最后一个元素放到堆顶
        array[size - 1] = null;//最后一个元素置空
        size--;
        downAdjustmentHeap();
        return e;
    }

    /**
     * 删除元素之后的向下调整
     */
    private void downAdjustmentHeap() {
        int parentIndex = 0;
        while (parentIndex * 2 + 1 < size) {
            int leftIndex = parentIndex * 2 + 1;
            int rightIndex = parentIndex * 2 + 2;
            int leftPriority = array[leftIndex].priority();
            int parentPriority = array[parentIndex].priority();
            //如果有右孩子
            if (rightIndex < size) {
                int rightPriority = array[rightIndex].priority();
                if (leftPriority > rightPriority && leftPriority > parentPriority) {
                    swap(leftIndex, parentIndex);
                    parentIndex = leftIndex;
                } else if (rightPriority > leftPriority && rightPriority > parentPriority) {
                    swap(rightIndex, parentIndex);
                    parentIndex = rightIndex;
                } else {
                    return;
                }
            } else {//没有右孩子,只需要判断当前节点和左孩子节点的优先级即可结束，因为其左孩子一定没有字节点了
                if (leftPriority > parentPriority)
                    swap(leftIndex, parentIndex);
                return;
            }
        }
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

}
