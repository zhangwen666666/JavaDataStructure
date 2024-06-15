package deque;

import java.util.Iterator;

public class LinkedListDeque<E> implements Deque<E>,Iterable<E>{
    static class Node<E>{
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Node<E> sentinel = new Node<>(null,null,null);

    public LinkedListDeque() {
        this(Integer.MAX_VALUE); //默认容量是int的最大值
    }

    public LinkedListDeque(int capacity) {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        this.capacity = capacity;
    }

    @Override
    public boolean offerFirst(E e) {
        if(isFull())
            return false;
        Node<E> node = new Node<>(sentinel,e,sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if(isFull())
            return false;
        Node<E> node = new Node<>(sentinel.prev,e,sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if(isEmpty())
            return null;
        Node<E> node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        size--;
        return node.value;
    }

    @Override
    public E pollLast() {
        if(isEmpty())
            return null;
        Node<E> node = sentinel.prev;
        sentinel.prev = node.prev;
        node.prev.next=sentinel;
        size--;
        return node.value;
    }

    @Override
    public E peekFirst() {
        if(isEmpty())
            return null;
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if(isEmpty())
            return null;
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> cur = sentinel.next;
            @Override
            public boolean hasNext() {
                return cur!=sentinel;
            }

            @Override
            public E next() {
                E returnVal = cur.value;
                cur = cur.next;
                return returnVal;
            }
        };
    }
}
