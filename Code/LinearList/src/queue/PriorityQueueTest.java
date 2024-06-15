package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PriorityQueueTest {
    @Test
    public void test(){
        PriorityQueue3<Entry> queue = new PriorityQueue3<>(5);
        queue.offer(new Entry("task1",4));
        queue.offer(new Entry("task2",3));
        queue.offer(new Entry("task3",2));
        queue.offer(new Entry("task4",5));
        queue.offer(new Entry("task5",1));
        assertFalse(queue.offer(new Entry("task6",6)));

        assertEquals(5,queue.poll().priority());
        assertEquals(4,queue.poll().priority());
        assertEquals(3,queue.poll().priority());
        assertEquals(2,queue.poll().priority());
        assertEquals(1,queue.poll().priority());
    }
}
