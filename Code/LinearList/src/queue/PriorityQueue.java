package queue;

public class PriorityQueue<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull())
            return false;
        array[size++] = value;
        return true;
    }

    /**
     * @return 返回优先级最高的索引值
     */
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority())
                max = i;
        }
        return max;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        int maxIndex = selectMax();
        E e = ((E) array[maxIndex]);
        remove(maxIndex);
        return e;
    }

    /**
     * 功能： 删除指定索引处的元素
     *
     * @param maxIndex 被删除元素的索引
     */
    private void remove(int maxIndex) {
        if (maxIndex < size - 1)
            System.arraycopy(array, maxIndex + 1, array, maxIndex, size - maxIndex - 1);
        array[--size] = null;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return (E) array[selectMax()];
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
