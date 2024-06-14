package queue;

import java.util.Iterator;
import java.util.List;

public class LinkedListQueue<E> implements Queue<E>,Iterable<E>{

    //节点类
    private static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    Node<E> head = new Node<>(null,null);
    Node<E> tail = head;
    private int size = 0;//节点数
    private int capacity = Integer.MAX_VALUE;//容量

    public LinkedListQueue(int capacity) {
        this();
        this.capacity = capacity;
    }


    public LinkedListQueue() {
        head.next = head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cur = head.next;
            @Override
            public boolean hasNext() {
                return cur!=head;
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
    public boolean offer(E value) {
        if(isFull())
            return false;
        tail.next = new Node<>(value,head);
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty())
            return null;
        Node<E> first = head.next;
        head.next = first.next;
        if(first == tail)
            tail = head;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if(isEmpty())
            return null;
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }
}
