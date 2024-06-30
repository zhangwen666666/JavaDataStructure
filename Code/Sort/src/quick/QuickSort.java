package quick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static merge.MergeSortBottomUp.getArray;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class QuickSort {
    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(arr, left, right);
        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        /*//单边循环找基准点
        int monitor = arr[right];
        int small = 0;
        int large = 0;
        while (small < right) {
            if (arr[small] < monitor) {
                if (arr[large] > monitor) {
                    //交换
                    swap(arr,large,small);
                    large++;
                    small++;
                    continue;
                }
            }
            if (arr[large] <= monitor) {
                large++;
            }
            small++;
        }
        swap(arr,large,right);
        return large;
         */

        //双边循环
        int index = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(arr, left, index);
        int monitor = arr[left];
        int large = left + 1;
        int small = right;
        while (large <= small) {
            while (large <= small && arr[large] < monitor) {
                large++;
            }
            while (large <= small && arr[small] > monitor) {
                small--;
            }
            if (large <= small) {
                swap(arr, small, large);
                large++;
                small--;
            }
        }
        swap(arr, left, small);
        return small;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
//        int[] array = {4, 2, 1, 0, 3};
        int len = 20;
        int[] array = getArray(len);
        System.out.println(Arrays.toString(array));
        quickSort(array);
        System.out.println(Arrays.toString(array));
        List<Integer> expect = new ArrayList<>();
        List<Integer> array1 = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            expect.add(i);
            array1.add(array[i]);
        }
        assertIterableEquals(expect, array1);
    }
}
