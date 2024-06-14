package stack;

import queue.LinkedListQueue;

import java.util.Iterator;

public class LinkedListStack<E> implements Stack<E>, Iterable<E> {
    private int size = 0;
    private int capacity = Integer.MAX_VALUE;
    private final Node<E> top = new Node<>(null, null);

    public LinkedListStack() {
    }

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    //节点类
    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cur = top.next;
            @Override
            public boolean hasNext() {
                return cur!=null;
            }

            @Override
            public E next() {
                E value = cur.value;
                cur = cur.next;
                return value;
            }
        };
    }

    @Override
    public boolean push(E value) {
        if(isFull())
            return false;
        top.next = new Node<>(value,top.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty())
            return null;
        E value = top.next.value;
        top.next = top.next.next;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty())
            return null;
        return top.next.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
