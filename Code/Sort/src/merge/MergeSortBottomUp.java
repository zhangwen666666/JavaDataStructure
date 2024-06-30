package merge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class MergeSortBottomUp {
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

    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] tmp = new int[len];
        for (int width = 1; width < len; width *= 2) {
            for (int left = 0; left < len; left += width * 2) {
                int mid = Math.min(left + width - 1, len - 1);
                int right = Math.min(left + 2 * width - 1, len - 1);
                merge(arr, left, mid, mid + 1, right, tmp);
            }
            System.arraycopy(tmp,0,arr,0,len);
        }
    }

    public static void main(String[] args) {
        int[] array = getArray(5);
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static int[] getArray(int length) {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        while (list.size() < length) {
            int num = random.nextInt(length);
            if(!list.contains(num)){
                list.add(num);
            }
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
