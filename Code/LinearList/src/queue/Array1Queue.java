package queue;

import java.util.Iterator;

public class Array1Queue<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    private int tail = 0;

    public Array1Queue(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offer(E value) {
        if (isFull())
            return false;
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        E returnVal = array[head];
        head = (head + 1) % array.length;
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
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cur = head;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public E next() {
                E returnVal = array[cur];
                cur = (cur + 1) % array.length;
                return returnVal;
            }
        };
    }
}
