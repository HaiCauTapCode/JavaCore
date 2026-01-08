package ss4.exercise.Bai11;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số phần tử: ");
        int n = sc.nextInt();

        int[] arr = inputArray(n);

        System.out.print("Mảng ban đầu: ");
        printArray(arr);

        System.out.print("Mảng sau khi đảo: ");
        reverseArray(arr);
        printArray(arr);
    }

    public static int[] inputArray(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        return arr;
    }

    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

