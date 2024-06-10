import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DynamicArrayTest {
    @Test
    public void testAdd() {
        DynamicArray dynamicArray = new DynamicArray();
        System.out.println(dynamicArray);
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        dynamicArray.addFirst(7);
        dynamicArray.addFirst(8);
        dynamicArray.addFirst(8);
        System.out.println(dynamicArray);
//        dynamicArray.foreach(System.out::println);
//
        Iterator<Integer> it = dynamicArray.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testRemove() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        //System.out.println(dynamicArray);

        int removed = dynamicArray.remove(4);
        assertEquals(5, removed);
        assertIterableEquals(List.of(1, 2, 3, 4), dynamicArray);
        //dynamicArray.stream().forEach(System.out::println);
    }

    @Test
    public void testCheckAndGrow() {
        DynamicArray dynamicArray = new DynamicArray();
        for (int i = 0; i < 10; i++) {
            dynamicArray.add(i, i);
        }
        assertIterableEquals(List.of(0,1,2,3,4,5,6,7,8,9),dynamicArray);
    }
}
