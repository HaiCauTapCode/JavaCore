package ss5.practice.Bai1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập họ tên: ");
        String name = sc.nextLine();

        System.out.print("Nhập số thứ tự: ");
        int index = sc.nextInt();

        String formattedName = DataCleaner.formatName(name);
        String id = DataCleaner.generateID(name, index);

        System.out.println("Tên sau khi chuẩn hóa: " + formattedName);
        System.out.println("Id: " + id);

    }
}
