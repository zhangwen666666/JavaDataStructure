package insertSort;

import java.util.Arrays;

public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    arr[j + 1] = value;
                    break;
                }
            }
            if (j == -1)
                arr[0] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr= {7,4,8,3,1,6,5,9,2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
