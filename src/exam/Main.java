package exam;

import exam.service.Service;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== HỆ THỐNG QUẢN LÝ KHO HÀNG =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm theo tên");
            System.out.println("4. Xóa sản phẩm theo Id");
            System.out.println("5. Hiển thị kho (đã sắp xếp theo giá giảm dần)");
            System.out.println("6. Thống kê giá trị kho");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 0:
                    System.out.println("Thoát chương trình thành công!!!");
                    return;
                case 1:
                    Service.gI().addProduct();
                    break;
                case 2:
                    Service.gI().showAll();
                    break;
                case 3:
                    Service.gI().searchByName();
                    break;
                case 4:
                    Service.gI().deleteById();
                    break;
                case 5:
                    Service.gI().showSortedByPriceDesc();
                    break;
                case 6:
                    Service.gI().statistic();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại");
            }
        }
    }
}
