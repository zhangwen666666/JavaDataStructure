package maxheap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

public class MaxHeapTest {
    @Test
    public void test(){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(2);
        set.add(7);
    }

    @Test
    public void test1(){
        MaxHeap<Integer> heap = new MaxHeap<>(new Integer[]{2, 4, 9, 5, 3, 7}, (o1,o2)->o1-o2);
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
    }

    @Test
    public void test2(){
        Person[] people = new Person[6];
        people[0] = new Person(2);
        people[1] = new Person(4);
        people[2] = new Person(1);
        people[3] = new Person(5);
        people[4] = new Person(3);
        people[5] = new Person(6);
        MaxHeap<Person> heap = new MaxHeap<>(people);
        System.out.println(heap);
    }
}
