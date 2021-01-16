package sortings;

import java.util.Arrays;

/**
 * Quick sorting
 */
public class QuickSort {
    int[] arr;

    public QuickSort(int[] arr, boolean sortInitially) {
        int len = arr.length;
        this.arr = Arrays.copyOf(arr, len);

        if (sortInitially) {
            this.sort(0, len - 1);
        }
    }

    private int makePivot(int start, int end) {
        int pivot = this.arr[end];
        int i = start - 1;

        for (int j = start; j <= end - 1; j++) {
            if (this.arr[j] <= pivot) {
                i++;
                this.swap(i, j);
            }
        }
        this.swap(i + 1, end);

        return i + 1;
    }

    public void sort(int start, int end) {
        if (end > start) {
            int pivotIndex = this.makePivot(start, end);

            this.sort(start, pivotIndex - 1);
            this.sort(pivotIndex + 1, end);
        }
    }

    private void swap(int start, int end) {
        while (end != start) {
            int temp = this.arr[end];
            this.arr[end] = this.arr[end - 1];
            this.arr[end - 1] = temp;
            end -= 1;
        }
    }
}
