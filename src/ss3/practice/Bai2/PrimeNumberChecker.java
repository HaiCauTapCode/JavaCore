package ss3.practice.Bai2;

import java.util.Scanner;

public class PrimeNumberChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số nguyên dương n: ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("Không có số nguyên tố nào.");
            return;
        }

        System.out.print("Các số nguyên tố từ 2 đến " + n + " là: ");

        for (int i = 2; i <= n; i++) {

            boolean isPrime = true;

            for (int j = 2; j <= Math.sqrt(i); j++) {

                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(i + " ");
            }
        }
    }
}

