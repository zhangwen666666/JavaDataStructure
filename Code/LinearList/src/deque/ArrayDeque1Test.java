package deque;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayDeque1Test {
    @Test
    public void testOffer(){
        ArrayDeque1<Integer> deque = new ArrayDeque1<>(5);
        System.out.println(deque.offerFirst(1));
        System.out.println(deque.offerFirst(2));
        System.out.println(deque.offerFirst(3));
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
        ArrayDeque1<Integer> deque = new ArrayDeque1<>(5);
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


