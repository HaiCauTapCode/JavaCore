package ss4.practice.Bai4;

import java.util.Scanner;

public class Bai4 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số phần tử n: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        inputArray(arr);
        System.out.println("Tất cả các phần tử trong mảng: ");
        outputArray(arr);

        System.out.print("Giá trị lớn nhất trong mảng: " + findMax(arr) + "\n");
        System.out.print("Giá trị nhỏ nhất trong mảng: " + findMin(arr) + "\n");

        sortAscending(arr);
        System.out.println("mảng sau khi sắp xếp tăng dần:");
        outputArray(arr);

        System.out.println("Phần tử lớn thứ 2 của mảng là: " + arr[arr.length - 2]);

        System.out.print("Nhập số x cần tìm: ");
        int x = sc.nextInt();

        System.out.print("Vị trí của tất cả phần tử " + x + " trong mảng là: ");
        findAndPrintPositions(arr, x);
    }

    public static void inputArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }
    }

    public static void outputArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
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

    public static void sortAscending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void findAndPrintPositions(int[] arr, int x) {
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                System.out.print(i + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.print("Không tìm thấy");
        }

        System.out.println();
    }

}

