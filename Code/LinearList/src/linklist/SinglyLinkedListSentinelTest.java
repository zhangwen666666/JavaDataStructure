package linklist;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class SinglyLinkedListSentinelTest {
    @Test
    public void testAddLast() {
        SinglyLinkedListSentinel<Integer> list = new SinglyLinkedListSentinel<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list.get(3));
    }

    @Test
    public void testAddFirst() {
        SinglyLinkedListSentinel<Integer> list = new SinglyLinkedListSentinel<>();
        list.loop(value -> System.out.print(value + " "));
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.loop(value -> System.out.print(value + " "));

    }

    @Test
    public void testAdd() {
        SinglyLinkedListSentinel<Integer> list = new SinglyLinkedListSentinel<>();
        list.addFirst(1);
        list.addLast(2);
        list.loop(value -> System.out.print(value + " "));
        System.out.println();
        list.insert(1, 66);
        list.loop(value -> System.out.print(value + " "));
    }

    @Test
    public void testRemoveFirst() {
        SinglyLinkedListSentinel<Integer> list = new SinglyLinkedListSentinel<>();
        list.addFirst(1);
        list.addLast(2);
        int i = list.removeFirst();
        System.out.println(i);
        i = list.removeFirst();
        System.out.println(i);
/*        i = list.removeFirst();
        System.out.println(i);*/
    }

    @Test
    public void testRemove() {
        SinglyLinkedListSentinel<Integer> list = new SinglyLinkedListSentinel<>();
        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(5);
        list.addLast(2);
        list.addLast(4);
        list.addLast(6);
        list.loop(var-> System.out.print(var + " "));
        System.out.println();
        int i = list.remove(5);
        System.out.println(i);
        list.loop(var-> System.out.print(var + " "));

    }
}
