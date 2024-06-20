package minheap;

import maxheap.MaxHeap;
import maxheap.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

public class MinHeapTest {

    @Test
    public void test1(){
        MinHeap<Integer> heap = new MinHeap<>(new Integer[]{2, 4, 9, 5, 3, 7}, (o1, o2)->o1-o2);
        System.out.println(heap);
        /*MaxHeap<Integer> heap1 = new MaxHeap<>(null);
        heap1.peek();*/
        System.out.println(heap.peek());
        System.out.println(heap.poll());
        System.out.println(heap);
        heap.offer(6);
        System.out.println(heap);
        heap.replace(1);
        System.out.println(heap);
        Integer[] list = new Integer[]{2, 4, 9, 5, 3, 7};
        System.out.println(Arrays.toString(list));
        MinHeap.heapSorted(list);
        System.out.println(Arrays.toString(list));
    }

    @Test
    public void test2(){
        Person[] people = new Person[6];
        people[0] = new maxheap.Person(2);
        people[1] = new maxheap.Person(4);
        people[2] = new maxheap.Person(1);
        people[3] = new maxheap.Person(5);
        people[4] = new maxheap.Person(3);
        people[5] = new maxheap.Person(6);
        MinHeap<Person> heap = new MinHeap<>(people);
        System.out.println(heap);
    }
}
