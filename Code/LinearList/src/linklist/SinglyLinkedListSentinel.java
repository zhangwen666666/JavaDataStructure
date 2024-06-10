package linklist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表的实现（带头结点）
 */
public class SinglyLinkedListSentinel<T> implements Iterable<T> {

    private Node<T> head = new Node<>(null, null);//头结点

    /**
     * 单向链表中的节点
     */
    private static class Node<T> {
        T value; //值
        Node<T> next; //下一个节点的引用

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 头插法，在链表头部插入元素
     */
    public void addFirst(T value) {
        head.next = new Node<>(value, head.next);
    }

    /**
     * 向链表尾部插入元素
     *
     * @param value 要插入的元素
     */
    public void addLast(T value) {
        Node<T> last = findLast();
        last.next = new Node<>(value, null);
    }

    /**
     * 向指定索引index处插入值value
     *
     * @param index 索引
     * @param value 插入值
     */
    public void insert(int index, T value) {
        if (index == 0)
            addFirst(value);
        else if (index < 0) {
            throw new RuntimeException("索引非法");
        } else {
            Node<T> pre = findNode(index - 1);
            if (pre == null)
                throw new RuntimeException("索引非法");
            pre.next = new Node<>(value, pre.next);
        }
    }

    /**
     * 删除首元素
     *
     * @return 被删除的元素值
     */
    public T removeFirst() {
        if (head.next == null)
            throw new RuntimeException("链表为空，无法删除首元素");
        T returnVal = head.next.value;
        head.next = head.next.next;
        return returnVal;
    }

    /**
     * 删除指定索引处的元素
     *
     * @param index 索引
     * @return 返回被删除的元素值
     */
    public T remove(int index) {
        if (index < 0)
            throw new RuntimeException("索引非法");
        else if (index == 0)
            return removeFirst();
        else {
            Node<T> node = findNode(index - 1);
            if (node == null || node.next == null)
                throw new RuntimeException("索引非法");
            T returnVal = node.next.value;
            node.next = node.next.next;
            return returnVal;
        }
    }

    /**
     * 找尾结点
     *
     * @return 返回尾结点，返回null表示链表为空
     */
    private Node<T> findLast() {
        Node<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 寻找指定索引处的节点
     *
     * @param index 索引
     * @return 指定索引处的节点，null表示不存在
     */
    private Node<T> findNode(int index) {
        if (index < 0)
            return null;
        Node<T> cur = head.next;
        while (cur != null) {
            if (index == 0)
                return cur;
            cur = cur.next;
            index--;
        }
        return null;
    }

    /**
     * 获取指定索引处的节点的值
     *
     * @param index 索引
     * @return 索引处节点的值，索引非法时抛出异常
     */
    public T get(int index) {
        Node<T> node = findNode(index);
        if (node == null) {
            throw new RuntimeException("索引非法");
        }
        return node.value;
    }

    /**
     * 遍历方法一
     *
     * @param consumer 函数式接口，表示遍历中的具体操作
     */
    public void loop(Consumer<T> consumer) {
        Node<T> cur = head.next;
        while (cur != null) {
            consumer.accept(cur.value);
            cur = cur.next;
        }
    }

    /**
     * 遍历方法二：获取迭代器,用来遍历链表
     *
     * @return 链表的迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                T curValue = cur.value;
                cur = cur.next;
                return curValue;
            }
        };
    }
}

