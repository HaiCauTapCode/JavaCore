package ss3.exercise.lesson3;

import java.util.Scanner;

public class ComputerPerformance {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập dung lượng RAM (GB): ");
        int ram = scanner.nextInt();

        System.out.print("Máy có SSD không? (true/false): ");
        boolean hasSSD = scanner.nextBoolean();

        if (ram >= 16 && hasSSD) {
            System.out.println("Loại VIP");
        } else if (ram >= 8 && ram < 16 && hasSSD) {
            System.out.println("Loại Khá");
        } else if (ram >= 8 && !hasSSD) {
            System.out.println("Loại Trung bình");
        } else {
            System.out.println("Loại Yếu");
        }

    }
}

