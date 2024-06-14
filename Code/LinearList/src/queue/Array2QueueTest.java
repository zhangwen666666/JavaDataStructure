package queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Array2QueueTest {
    @Test
    public void test1(){
        Array2Queue<Integer> queue = new Array2Queue<>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        assertIterableEquals(queue, List.of(1,2,3,4,5));
    }

    @Test
    public void test2(){
        Array2Queue<Integer> queue = new Array2Queue<>(5);
        assertNull(queue.peek());
        queue.offer(1);
        assertEquals(queue.peek(),1);
        queue.offer(2);
        assertEquals(queue.peek(),1);
        queue.offer(3);
        assertEquals(queue.peek(),1);
        queue.offer(4);
        assertEquals(queue.peek(),1);
        queue.offer(5);
        assertEquals(queue.peek(),1);
    }

    @Test
    public void test3(){
        Array2Queue<Integer> queue = new Array2Queue<>(4);
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
        Array2Queue<Integer> queue = new Array2Queue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertFalse(queue.offer(4));
        assertFalse(queue.offer(5));
        assertIterableEquals(List.of(1,2,3),queue);
    }
}
