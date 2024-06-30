package Bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static insertSort.InsertSort.insertSort;

public class BucketSort {
    public static void sort(int[] ages){

        List<Integer>[] buckets = new List[10];
        int k = 0;
        for(int i = 0;i< buckets.length;i++){
            buckets[i] = new ArrayList<>();
        }
        for (int age : ages) {
            buckets[age/10].addLast(age);
        }
//        print(buckets);
        for (List<Integer> bucket : buckets) {
            int[] array = new int[bucket.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = bucket.get(i);
            }
            insertSort(array);
            for (int i = 0; i < array.length; i++) {
                bucket.set(i,array[i]);
                ages[k++] = array[i];
            }
        }
        //print(buckets);
    }

    public static void print(List<Integer>[] buckets){
        for (List<Integer> bucket : buckets) {
            System.out.println(bucket);
        }
    }

    public static void main(String[] args) {
        int len = 15;
        int[] arr = getArray(len, 1, 99);
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
