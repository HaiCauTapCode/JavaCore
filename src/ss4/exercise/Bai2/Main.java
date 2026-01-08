package ss4.exercise.Bai2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập chuỗi: ");
        String input = sc.nextLine();

        System.out.println("Độ dài chuỗi: " + input.length());

        System.out.println("Chuỗi chữ hoa: " + input.toUpperCase());

        System.out.println("Chuỗi chữ thường: " + input.toLowerCase());

        String trimmed = input.trim();
        System.out.println("Chuỗi sau khi trim: " + trimmed);
        System.out.println("Độ dài sau khi trim: " + trimmed.length());
    }
}

