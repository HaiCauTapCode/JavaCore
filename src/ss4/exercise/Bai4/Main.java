package ss4.exercise.Bai4;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số phần tử n: ");
        int n = sc.nextInt();
        int[] arr = inputArray(n);

        System.out.print("Mảng ban đầu: ");
        printArray(arr);

        System.out.println("Giá trị lớn nhất: " + findMax(arr));
        System.out.println("Giá trị nhỏ nhất: " + findMin(arr));

        System.out.print("Mảng sau khi sắp xếp tăng dần: ");
        sortAscending(arr);
        printArray(arr);
    }

    public static int[] inputArray(int n) {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        return arr;
    }

    public static int findMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    // 10 9 8 7 6 5 4 3 2 1
    public static void sortAscending(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
