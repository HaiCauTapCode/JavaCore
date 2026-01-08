package ss4.practice.Bai15;

import java.util.Scanner;

public class Bai15 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Nhập n: ");
        int n = sc.nextInt();

        int[][] a = new int[n][n];

        inputMatrix(a);

        System.out.println("\nMa trận vừa nhập:");
        outputMatrix(a);

        int totalSum = sumAllElements(a);
        int mainDiagonalSum = sumMainDiagonal(a);
        int secondaryDiagonalSum = sumSecondaryDiagonal(a);

        System.out.println("\nTong tat ca phan tu = " + totalSum);
        System.out.println("Tong duong cheo chinh = " + mainDiagonalSum);
        System.out.println("Tong duong cheo phu = " + secondaryDiagonalSum);

    }

    public static void inputMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print("a[" + i + "][" + j + "] = ");
                a[i][j] = sc.nextInt();
            }
        }
    }

    public static void outputMatrix(int[][] a) {
        for (int[] i : a) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(i[j] + "\t");
            }
            System.out.println();
        }
    }

    public static int sumAllElements(int[][] a) {
        int sum = 0;

        for (int[] i : a) {
            for (int j = 0; j < a.length; j++) {
                sum += i[j];
            }
        }

        return sum;
    }

    public static int sumMainDiagonal(int[][] a) {
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i][i];
        }

        return sum;
    }

    /*
        1   2   3   4
        5   6   7   8
        9  10  11  12
        13 14  15  16
    */
    public static int sumSecondaryDiagonal(int[][] a) {
        int sum = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            sum += a[i][n - 1 - i];
        }

        return sum;
    }
}
