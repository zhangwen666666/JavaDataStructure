package queue;

/**
 * 基于有序数组实现的优先级队列
 *
 * @param <E> 优先级队列所存储的元素类型
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull())
            return false;
        int i = size - 1;
        for (; i >= 0 && array[i].priority() >= value.priority(); i--) {
            array[i + 1] = array[i];
        }
        array[i + 1] = value;
        size++;
        return true;
    }


    @Override
    public E poll() {
        if (isEmpty())
            return null;
        E e = (E) array[size - 1];
        array[--size] = null;
        return e;
    }


    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return (E) array[size - 1];
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
