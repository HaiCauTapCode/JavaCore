package test.algorithms.sorting;

/*
    sắp xếp chèn
*/

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {7, 3, 5, 2, 9, 1, 4};

        System.out.println("Mảng ban đầu:");
        printArray(arr);

        myInsertionSort(arr);

        System.out.println("\nMảng sau khi sắp xếp:");
        printArray(arr);
    }

    private static void myInsertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }


    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}

