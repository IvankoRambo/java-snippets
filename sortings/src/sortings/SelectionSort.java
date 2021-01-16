package sortings;

import java.util.Arrays;

/**
 * Selection sorting
 */
public class SelectionSort {
    int[] arr;

    public SelectionSort(int[] arr) {
        int len = arr.length;
        this.arr = Arrays.copyOf(arr, len);
    }

    public void sort() {
        int len = arr.length;
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                int jMin = i;
                for (int j = i + 1; j < len; j++) {
                    if (arr[j] < arr[jMin]) {
                        jMin = j;
                    }
                }

                if (jMin != i) {
                    this.swap(i, jMin);
                }
            }
        }
    }

    private void swap(int start, int end) {
        while (end != start) {
            int temp = this.arr[end];
            this.arr[end] = this.arr[end-1];
            this.arr[end-1] = temp;
            end -= 1;
        }
    }
}
