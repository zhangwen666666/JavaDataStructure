package shell;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int gap = arr.length >> 1;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int value = arr[i];
                int j;
                for (j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > value) {
                        arr[j + gap] = arr[j];
                    } else {
                        arr[j + gap] = value;
                        break;
                    }
                }
                if (j < 0)
                    arr[j + gap] = value;
            }
            gap = gap >> 1;
        }
    }

    public static void sort(int[] a) {
        for (int gap = a.length >> 1; gap > 0; gap = gap >> 1) {
            for (int low = gap; low < a.length; low++) {
                // 将 low 位置的元素插入至 [0..low-1] 的已排序区域
                int t = a[low];
                int i = low - gap; // 已排序区域指针

                while (i >= 0 && t < a[i]) { // 没有找到插入位置
                    a[i + gap] = a[i]; // 空出插入位置
                    i -= gap;
                }

                // 找到插入位置
                if (i != low - gap) {
                    a[i + gap] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 8, 3, 2, 6, 5, 9, 1};
        shellSort(arr);
//        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
