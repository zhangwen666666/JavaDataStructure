package deque;

import queue.Queue;

import java.util.Iterator;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {
    private final E[] array;
    private int head;
    private int tail;

    public ArrayDeque1() {
        this(1024);//默认容量为1024；
    }

    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull())
            return false;
        head = dec(head);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull())
            return false;
        array[tail] = e;
        tail = inc(tail);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty())
            return null;
        E e = array[head];
        array[head] = null;//帮助GC进行垃圾回收
        head = inc(head);
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty())
            return null;
        tail = dec(tail);
        E e = array[tail];
        array[tail] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty())
            return null;
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty())
            return null;
        return array[dec(tail)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (head < tail)
            return tail - head == array.length - 1;
        return head - tail == 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E e = array[p];
                p = inc(p);
                return e;
            }
        };
    }

    /**
     * @param i 当前索引值
     * @return 当前索引+1后合法的索引值
     */
    private int inc(int i) {
        if (i + 1 >= array.length)
            return 0;
        return i + 1;
    }

    /**
     * @param i 当前索引值
     * @return 当前索引-1之后合法的索引值
     */
    private int dec(int i) {
        if (i - 1 < 0)
            return array.length - 1;
        return i - 1;
    }
}
