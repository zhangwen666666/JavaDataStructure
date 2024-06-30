package radix;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        String[] phoneNumbers = new String[6];
        phoneNumbers[0] = "158";
        phoneNumbers[1] = "151";
        phoneNumbers[2] = "235";
        phoneNumbers[3] = "138";
        phoneNumbers[4] = "139";
        phoneNumbers[5] = "159";
        radixSort(phoneNumbers, 3);
        System.out.println(Arrays.toString(phoneNumbers));
    }

    private static void radixSort(String[] phoneNumbers, int length) {
        //1. 准备桶
        ArrayList<String>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i = length - 1; i >= 0; i--) {
            //将数据按照某一位放入桶里
            for (String phoneNumber : phoneNumbers) {
                buckets[phoneNumber.charAt(i) - '0'].add(phoneNumber);
            }
            int k = 0;
            //拿出桶里的内容放回数组
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    phoneNumbers[k++] = s;
                }
                bucket.clear();
            }
        }
    }
}
