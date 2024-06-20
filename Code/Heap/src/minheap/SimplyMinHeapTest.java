package minheap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SimplyMinHeapTest {
    @Test
    public void test(){
        int[] arr = {10,4,9,1,7,2,8};
        SimplyMinHeap heap = new SimplyMinHeap(arr);
        System.out.println(heap);
        heap.poll();
        System.out.println(heap);
        heap.offer(12);
        System.out.println(heap);
        System.out.println(Arrays.toString(arr));
        SimplyMinHeap.heapSorted(arr);
        System.out.println(heap);
    }
}
