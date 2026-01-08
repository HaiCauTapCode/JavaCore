package ss4.exercise.Bai8;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] matrix = inputMatrix();
        System.out.println("Tổng các phần tử trong ma trận: " + sumMatrix(matrix));
    }

    public static int[][] inputMatrix() {
        int[][] matrix = new int[3][3];
        int n = matrix.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print("Nhập phần tử [" + row + "][" + col + "]: ");
                matrix[row][col] = sc.nextInt();
            }
        }

        return matrix;
    }

    public static int sumMatrix(int[][] matrix) {
        int sum = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                sum += matrix[row][col];
            }
        }

        return sum;
    }
}

