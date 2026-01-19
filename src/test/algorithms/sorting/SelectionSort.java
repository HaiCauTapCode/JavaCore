package test.algorithms.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {7, 3, 5, 2, 9, 1, 4};

        System.out.println("Mảng ban đầu:");
        printArray(arr);

        selectionSort(arr);

        System.out.println("\nMảng sau khi sắp xếp:");
        printArray(arr);
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }


    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}

