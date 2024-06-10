import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {
    private int size = 0;   //元素个数
    private int capacity = 8;   //初始容量
    private int[] array = {};   //初始数组

    public void addLast(int elem) {
        add(size, elem);
    }

    public void addFirst(int elem) {
        add(0, elem);
    }

    public void add(int index, int elem) {
        if (index < 0 || index > size)
            throw new RuntimeException("插入位置非法");
        checkAndGrow(); //检查容量
        if (index < size) { //在中间插入时需要移动元素
            System.arraycopy(array, index, array, index + 1, size - index);//拷贝
        }
        array[index] = elem;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("索引位置非法");
        int removed = array[index];
        if (index < size - 1)
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    public void checkAndGrow() {
        if (array.length == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            //扩容
            capacity = (capacity >> 1) + capacity;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("索引位置非法");
        return array[index];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[ ");
        for (int i = 0; i < size; i++) {
            s.append(array[i]).append(" ");
        }
        s.append("]\n");
        return s.toString();
    }

    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
            consumer.accept(array[i]);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }
}
