package linklist;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class DoublyLinkedListTest {
    @Test
    public void testInsert(){
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        /*list.addLast(99);
        list.loop(var-> System.out.print(var + " "));
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        System.out.println();
        list.loop(var-> System.out.print(var + " "));
        list.insert(2,5);
        System.out.println();
        list.loop(var-> System.out.print(var + " "));
        list.addLast(66);
        System.out.println();
        list.loop(var-> System.out.print(var + " "));
        System.out.println("=================");*/
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void testRemove(){
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
       /* System.out.println(list.remove(0));
        list.loop(var-> System.out.print(var + " "));*/

        list.addFirst(1);
        /*list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);*/
        list.loop(var-> System.out.print(var + " "));
        System.out.println(list.removeLast());
        list.loop(var-> System.out.print(var + " "));
    }
}
