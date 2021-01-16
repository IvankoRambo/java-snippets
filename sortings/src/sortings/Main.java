package sortings;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 1, 7, 10, 4, 14, 3, 9, 3, 34, 54, 23, 6, 23, 78, 11, 3, 4, 34, 23, 65, 36, 87, 8};
        System.out.println("Initial array: " + Arrays.toString(arr));

        BubbleSort bubbleSort = new BubbleSort(arr);
        long bubbleStartTime = System.nanoTime();
        bubbleSort.sort();
        long bubbleEndTime = System.nanoTime();
        long bubbleElapsedTime = bubbleEndTime - bubbleStartTime;
        int[] bubbleSortedArr = bubbleSort.arr;
        System.out.println("Bubble sorting: " + Arrays.toString(bubbleSortedArr) + "; Time: " + bubbleElapsedTime + " ns");

        InsertionSort insertionSort = new InsertionSort(arr);
        long insertionStartTime = System.nanoTime();
        insertionSort.sort();
        long insertionEndTime = System.nanoTime();
        long insertionElapsedTime = insertionEndTime - insertionStartTime;
        int[] insertionSortedArr = insertionSort.arr;
        System.out.println("Insertion sorting: " + Arrays.toString(insertionSortedArr) + "; Time: " + insertionElapsedTime + " ns");

        SelectionSort selectionSort = new SelectionSort(arr);
        long selectionStartTime = System.nanoTime();
        selectionSort.sort();
        long selectionEndTime = System.nanoTime();
        long selectionElapsedTime = selectionEndTime - selectionStartTime;
        int[] selectionSortedArr = selectionSort.arr;
        System.out.println("Selection sorting: " + Arrays.toString(selectionSortedArr) + "; Time: " + selectionElapsedTime + " ns");

        MergeSort mergeSort = new MergeSort(arr, false);
        long mergeStartTime = System.nanoTime();
        mergeSort.sort(0, arr.length - 1);
        long mergeEndTime = System.nanoTime();
        long mergeElapsedTime = mergeEndTime - mergeStartTime;
        int[] mergeSortedArr = mergeSort.arr;
        System.out.println("Merge sorting: " + Arrays.toString(mergeSortedArr) + "; Time: " + mergeElapsedTime + " ns");
    }
}
