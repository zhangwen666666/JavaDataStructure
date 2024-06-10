package biSearch;

public class BinarySearch {
    public static int binarySearchBasic(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (target < arr[m]) {
                j = m - 1;
            } else if (target > arr[m]) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static int binarySearchLeftmost(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }

    public static int searchInsertRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return target == nums[left - 1] ? left - 1 : left;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 4, 7, 7, 9, 10};
        System.out.println(searchInsertRight(arr, 7));
    }
}
