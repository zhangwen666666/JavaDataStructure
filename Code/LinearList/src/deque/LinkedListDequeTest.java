package deque;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListDequeTest {
    @Test
    public void testOffer(){
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);
        assertFalse(deque.offerLast(6));
        for(Integer i : deque){
            System.out.println(i);
        }
        assertIterableEquals(deque, List.of(3,2,1,4,5));
    }

    @Test
    public void poll(){
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);
        assertEquals(3,deque.pollFirst());
        assertEquals(5,deque.pollLast());
        assertEquals(4,deque.pollLast());
        assertEquals(2,deque.pollFirst());
        assertEquals(1,deque.pollFirst());
        assertNull(deque.pollFirst());
    }
}
