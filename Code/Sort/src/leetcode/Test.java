package leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 3};
        int[] sort = new Test().frequencySort(arr);
        System.out.println(Arrays.toString(sort));
    }

    public int[] frequencySort(int[] nums) {
        int[] count = new int[201];
        for (int j : nums) {
            count[j + 100]++;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((i1, i2) -> {
            if (count[i1 + 100] < count[i2 + 100]) {
                return -1;
            } else if (count[i1 + 100] > count[i2 + 100]) {
                return 1;
            } else {
                return i2 - i1;
            }
        });
        for (int i = 0; i < 201; i++) {
            map.put(i - 100, count[i]);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int[] array = new int[nums.length];
        int k = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            int value = entry.getValue();
            int key = entry.getKey();
            while (value != 0) {
                array[k++] = key;
                value--;
            }
        }
        return array;
    }
}

