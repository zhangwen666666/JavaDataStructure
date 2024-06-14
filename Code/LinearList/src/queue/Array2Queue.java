package queue;

import java.util.Iterator;

public class Array2Queue<E> implements Queue<E>, Iterable<E> {
    private final E[] array;
    private int head = 0;
    private int tail = 0;
    private int size = 0;//元素个数

    public Array2Queue(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull())
            return false;
        array[tail] = value;
        tail = (tail + 1) % array.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        E returnVal = array[head];
        head = (head + 1) % array.length;
        size--;
        return returnVal;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cur = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E returnVal = array[cur];
                cur = (cur + 1) % array.length;
                count++;
                return returnVal;
            }
        };
    }
}
