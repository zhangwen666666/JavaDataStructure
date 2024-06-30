package select;

import java.util.Arrays;
import java.util.Comparator;

public class SelectSort {
    public static <E extends Comparable<E>> void selectSort(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    public static <E> void selectSort(E[] arr, Comparator<E> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E e = arr[i];
        arr[i] = arr[j];
        arr[j] = e;
    }

    public static void main(String[] args) {
        Integer[] arr = {2, 7, 4, 6, 1, 9, 5, 3};
        selectSort(arr, (o1, o2) -> (o2 - o1));
        System.out.println(Arrays.toString(arr));
    }
}
