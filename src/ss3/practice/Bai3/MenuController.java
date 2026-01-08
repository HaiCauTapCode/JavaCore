package ss3.practice.Bai3;

import java.util.Scanner;

public class MenuController {

    public static int sumFrom1To100() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Tính tổng các số từ 1 đến 100");
            System.out.println("2. In bảng chữ cái từ A đến Z");
            System.out.println("3. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int sum = sumFrom1To100();
                    System.out.println("Tổng các số từ 1 đến 100 là: " + sum);
                    break;
                case 2:
                    System.out.println("Bảng chữ cái từ A đến Z:");
                    for (char c = 'A'; c <= 'Z'; c++) {
                        System.out.print(c + " ");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        } while (choice != 3);
    }
}
