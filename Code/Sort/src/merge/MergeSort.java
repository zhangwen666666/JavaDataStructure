package merge;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        split(arr, 0, arr.length - 1, tmp);
    }

    private static void split(int[] arr, int left, int right, int[] tmp) {
        if (left == right) {
            return;
        }
        int m = (left + right) >>> 1;
        split(arr, left, m, tmp);
        split(arr, m + 1, right, tmp);
        merge(arr, left, m, m + 1, right, tmp);
        System.arraycopy(tmp, left, arr, left, right - left + 1);
    }

    private static void merge(int[] arr1, int l1, int r1, int l2, int r2, int[] tmp) {
        int k = l1;
        while (l1 <= r1 && l2 <= r2) {
            if (arr1[l1] <= arr1[l2]) {
                tmp[k++] = arr1[l1++];
            } else {
                tmp[k++] = arr1[l2++];
            }
        }
        if (l1 > r1) {
            System.arraycopy(arr1, l2, tmp, k, r2 - l2 + 1);
        }
        if (l2 > r2) {
            System.arraycopy(arr1, l1, tmp, k, r1 - l1 + 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 3, 1, 8, 5, 7, 6, 2, 4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
