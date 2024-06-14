package queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListQueueTest {
    @Test
    public void test1(){
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        listQueue.offer(1);
        listQueue.offer(2);
        listQueue.offer(3);
        listQueue.offer(4);
        listQueue.offer(5);
        assertIterableEquals(listQueue, List.of(1,2,3,4,5));
    }

    @Test
    public void test2(){
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        assertNull(listQueue.peek());
        listQueue.offer(1);
        assertEquals(listQueue.peek(),1);
        listQueue.offer(2);
        assertEquals(listQueue.peek(),1);
        listQueue.offer(3);
        assertEquals(listQueue.peek(),1);
        listQueue.offer(4);
        assertEquals(listQueue.peek(),1);
        listQueue.offer(5);
        assertEquals(listQueue.peek(),1);
    }

    @Test
    public void test3(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        assertEquals(queue.poll(),1);
        assertEquals(queue.poll(),2);
        assertEquals(queue.poll(),3);
        assertEquals(queue.poll(),4);
        assertNull(queue.poll());
    }

    @Test
    public void test4(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertFalse(queue.offer(4));
        assertFalse(queue.offer(5));
        assertIterableEquals(List.of(1,2,3),queue);
    }
}
