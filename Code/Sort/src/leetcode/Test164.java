package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test164 {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        sort(nums);
        System.out.println(Arrays.toString(nums));
        int gap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            gap = Math.max(Math.abs(nums[i] - nums[i + 1]), gap);
        }
        return gap;
    }

    public static void sort(int[] ages) {
        int max = ages[0];
        int min = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] > max) {
                max = ages[i];
            }
            if (ages[i] < min) {
                min = ages[i];
            }
        }
        int range = Math.max((max - min) / (ages.length - 1), 1);
        List<Integer>[] buckets = new List[(max - min) / range + 1];
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int age : ages) {
            buckets[(age - min) / range].addLast(age);
        }
        for (List<Integer> bucket : buckets) {
            bucket.sort((o1, o2) -> o1 - o2);
            int[] array = new int[bucket.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = bucket.get(i);
            }
            for (int i = 0; i < array.length; i++) {
                bucket.set(i, array[i]);
                ages[k++] = array[i];
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {100, 3, 2, 1};
        System.out.println(new Test164().maximumGap(arr));
    }
}
