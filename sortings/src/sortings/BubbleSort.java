package sortings;

import java.util.Arrays;

/**
 * Bubble sorting class
 */
class BubbleSort {
    int[] arr;

    BubbleSort(int[] arr) {
        int len = arr.length;
        this.arr = Arrays.copyOf(arr, len);
    }

    public void sort() {
        int len = arr.length;
        if (len > 1) {
            for (int i = 0; i < len - 1; i++) {
                boolean sorted = true;
                for (int j = 0; j < len - 1 - i; j++) {
                    if (arr[j + 1] < arr[j]) {
                        this.swap(j);
                        if (sorted) {
                            sorted = false;
                        }
                    }
                }
                if (sorted) {
                    break;
                }
            }
        }
    }

    private void swap(int index) {
        int temp = this.arr[index];
        this.arr[index] = this.arr[index + 1];
        this.arr[index + 1] = temp;
    }
}
