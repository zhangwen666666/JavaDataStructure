package heap;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort {
    public static <E extends Comparable<E>> void heapify(E[] array, int size) {
        for (int i = (size - 1) / 2; i >= 0; i--) {
            down(array, i, size);
        }
    }

    private static <E> void heapify(E[] array, int size, Comparator<E> comparator) {
        for (int i = (size - 1) / 2; i >= 0; i--) {
            down(array, i, size, comparator);
        }
    }

    private static <E extends Comparable<E>> void down(E[] array, int i, int size) {
        int parent = i;
        while (2 * parent + 1 < size) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int max = parent;
            if (array[left].compareTo(array[max]) > 0) {
                max = left;
            }
            if (right < size && array[right].compareTo(array[max]) > 0) {
                max = right;
            }
            if (max == parent) {
                return;
            }
            swap(array, max, parent);
            parent = max;
        }
    }

    private static <E> void down(E[] array, int i, int size, Comparator<E> comparator) {
        int parent = i;
        while (2 * parent + 1 < size) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            int max = parent;
            if (comparator.compare(array[left], array[max]) > 0) {
                max = left;
            }
            if (right < size && comparator.compare(array[right], array[max]) > 0) {
                max = right;
            }
            if (max == parent) {
                return;
            }
            swap(array, max, parent);
            parent = max;
        }
    }

    private static <E> void swap(E[] array, int max, int parent) {
        E e = array[max];
        array[max] = array[parent];
        array[parent] = e;
    }

    public static <E extends Comparable<E>> void heapSort(E[] array) {
        heapify(array, array.length);
        for (int i = 0; i < array.length; i++) {
            swap(array, 0, array.length - 1 - i);
            down(array, 0, array.length - 1 - i);
        }
    }

    public static <E> void heapSort(E[] array, Comparator<E> comparator) {
        heapify(array, array.length, comparator);
        for (int i = 0; i < array.length; i++) {
            swap(array, 0, array.length - 1 - i);
            down(array, 0, array.length - 1 - i, comparator);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {2, 7, 4, 6, 1, 9, 5, 3};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        heapSort(arr, (o1, o2) -> o2 - o1);
        System.out.println(Arrays.toString(arr));

    }
}
