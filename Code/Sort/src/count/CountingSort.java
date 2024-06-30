package count;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CountingSort {
    public static void sort(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int j : a) {
            count[j - min]++;
        }
        System.out.println(Arrays.toString(count));
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] != 0) {
                a[k++] = i + min;
                count[i]--;
            }
        }
    }


    public static void main(String[] args) {
        int len = 15;
        int[] arr = getArray(len, 3, 7);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] getArray(int length, int min, int max) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }
}
