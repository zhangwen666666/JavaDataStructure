package linklist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 双向链表带头结点和尾指针
 */
public class DoublyLinkedList<T> implements Iterable<T> {
    private final Node<T> head;//头结点
    private final Node<T> tail;//尾结点

    /**
     * 构造方法，初始化一个空的带头结点和尾结点的双向链表
     */
    public DoublyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public T next() {
                T returnVal = cur.value;
                cur = cur.next;
                return returnVal;
            }
        };
    }

    /**
     * 节点类
     *
     * @param <T> 链表存储的元素的数据类型
     */
    private static class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(T value) {
        insert(0, value);
    }

    public void addLast(T value) {
        Node<T> tNode = new Node<>(tail.prev, value, tail);
        tail.prev.next = tNode;
        tail.prev = tNode;
    }

    public void insert(int index, T value) {
        Node<T> prevNode = findNode(index - 1);
        if (prevNode == null) {
            throw new RuntimeException("索引非法");
        }
        Node<T> tNode = new Node<>(prevNode, value, prevNode.next);
        prevNode.next.prev = tNode;
        prevNode.next = tNode;
    }

    public T remove(int index) {
        Node<T> prevNode = findNode(index - 1);
        if (prevNode == null)
            throw new RuntimeException("索引非法");
        Node<T> cur = prevNode.next;
        if (cur == tail)
            throw new RuntimeException("索引非法");
        prevNode.next = cur.next;
        cur.next.prev = cur.prev;
        return cur.value;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        Node<T> cur = tail.prev;
        if (cur == head)
            throw new RuntimeException("索引非法");
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        return cur.value;
    }


    /**
     * 找指定索引处的节点
     *
     * @param index 索引 索引为-1时找到的是头结点
     * @return 节点，返回null表示找不到
     */
    private Node<T> findNode(int index) {
        Node<T> cur = head;
        int i = -1;
        while (cur != tail && index >= -1) {
            if (i == index) {
                return cur;
            }
            i++;
            cur = cur.next;
        }
        return null;
    }

    public void loop(Consumer<T> consumer) {
        Node<T> cur = head.next;
        while (cur != tail) {
            consumer.accept(cur.value);
            cur = cur.next;
        }
    }
}
