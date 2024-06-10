package linklist;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SinglyLinkedListTest {
    @Test
    public void testAddFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.loop(value -> System.out.print(value + " "));
    }

    @Test
    public void testIterator() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addFirst("abc");
        list.addFirst("xyz");
        list.addFirst("hello");
        list.addFirst("world");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testAddLast() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("abc");
        list.addLast("def");
        list.addLast("xyz");
        list.addLast("puw");
        list.loop(value -> System.out.print(value + " "));
        assertIterableEquals(List.of("abc", "def", "xyz", "puw"), list);
    }

    @Test
    public void testGet() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("abc");
        list.addLast("def");
        list.addLast("xyz");
        list.addLast("puw");
        System.out.println(list.get(0));
    }

    @Test
    public void testInsert() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("abc");
        list.addLast("def");
        list.addLast("xyz");
        list.addLast("puw");
        list.insert(3, "hello");
        list.loop(value -> System.out.print(value + " "));
    }

    @Test
    public void testRemoveFirst() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        //list.removeFirst();
        list.addLast("abc");
        list.addLast("def");
        list.addLast("xyz");
        list.addLast("puw");
        list.loop(value -> System.out.print(value + " "));
        System.out.println();
        String s = list.removeFirst();
        list.loop(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("被删除的元素是: " + s);
        s = list.remove(2);
        list.loop(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("被删除的元素是: " + s);
    }
}
