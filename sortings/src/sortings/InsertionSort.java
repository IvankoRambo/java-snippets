package sortings;

import java.util.Arrays;

/**
 * Insertion sorting
 */
public class InsertionSort {
    int[] arr;

    InsertionSort(int[] arr) {
        int len = arr.length;
        this.arr = Arrays.copyOf(arr, len);
    }

    public void sort() {
        int len = this.arr.length;
        if (len > 1) {
            int i = 1;
            while (i < len) {
                int j = i;
                while (j > 0 && arr[j] < arr[j-1]) {
                    this.swap(j);
                    j -= 1;
                }
                i += 1;
            }
        }
    }

    private void swap(int index) {
        int temp = this.arr[index];
        this.arr[index] = this.arr[index - 1];
        this.arr[index - 1] = temp;
    }
}
