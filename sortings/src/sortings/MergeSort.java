package sortings;

import java.util.Arrays;

/**
 * Merge sorting
 */
public class MergeSort {
    int[] arr;

    public MergeSort(int[] arr, boolean sortInitially) {
        int len = arr.length;
        this.arr = Arrays.copyOf(arr, len);

        if (sortInitially) {
            this.sort(0, len - 1);
        }
    }

    private void merge(int start, int median, int end) {
        int leftArrLength = median - start + 1;
        int rightArrLength = end - median;
        int[] leftArr = new int[leftArrLength];
        int[] rightArr = new int[rightArrLength];

        for (int i = 0; i < leftArrLength; i++) {
            leftArr[i] = this.arr[start + i];
        }
        for (int j = 0; j < rightArrLength; j++) {
            rightArr[j] = this.arr[median + 1 + j];
        }

        int k = start;
        int i = 0;
        int j = 0;

        while (i < leftArrLength && j < rightArrLength) {
            if (leftArr[i] <= rightArr[j]) {
                this.arr[k] = leftArr[i];
                i++;
            } else {
                this.arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftArrLength) {
            this.arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightArrLength) {
            this.arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public void sort(int start, int end) {
        if (end > start) {
            int median = (start + end) / 2;

            this.sort(start, median);
            this.sort(median + 1, end);

            this.merge(start, median, end);
        }
    }
}
