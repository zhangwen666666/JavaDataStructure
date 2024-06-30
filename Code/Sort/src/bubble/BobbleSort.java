package bubble;

import java.util.Arrays;
import java.util.Comparator;

public class BobbleSort {
    public static <E extends Comparable<E>> void bubbleSort(E[] arr) {
        int end = arr.length - 1;//每轮待排序的右边界
        while (true) {
            int index = 0;//本轮最后一次交换时的位置
            for (int i = 0; i < end; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    E e = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = e;
                    index = i;
                }
            }
            if (index == 0)
                break;
            end = index;
        }
    }

    public static <E> void bubbleSort(E[] arr, Comparator<E> comparator) {
        int end = arr.length - 1;//每轮待排序的右边界
        while (true) {
            int index = 0;//本轮最后一次交换时的位置
            for (int i = 0; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    E e = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = e;
                    index = i;
                }
            }
            if (index == 0)
                break;
            end = index;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr, (i1, i2) -> i2 - i1);
        System.out.println(Arrays.toString(arr));
    }
}
