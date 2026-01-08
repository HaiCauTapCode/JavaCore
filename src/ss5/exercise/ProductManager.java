package ss5.exercise;

import java.util.Scanner;

public class ProductManager {
    public static final int MAX_PRODUCT = 100;
    public static Product[] storage = new Product[MAX_PRODUCT];
    public static int count = 0;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Logger.success("===== HỆ THỐNG QUẢN LÝ KHO HÀNG =====\n");

            Logger.success("1. Thêm sản phẩm\n");
            Logger.primary("2. Hiển thị và thống kê\n");
            Logger.log(Logger.CYAN, "3. Tìm sản phẩm rẻ nhất\n");
            Logger.warning("4. Cập nhật sản phẩm\n");
            Logger.log(Logger.RED, "5. Xóa sản phẩm\n");
            Logger.log(Logger.PURPLE_BOLD_BRIGHT, "6. Sắp xếp theo giá giảm dần\n");
            Logger.dark("0. Thoát\n");

            Logger.primary("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 0:
                    return;
                case 1:
                    ProductService.gI().addProducts();
                    break;
                case 2:
                    ProductService.gI().showAll();
                    break;
                case 3:
                    ProductService.gI().findCheapest();
                    break;
                case 4:
                    ProductService.gI().updateProduct();
                    break;
                case 5:
                    ProductService.gI().deleteProduct();
                    break;
                case 6:
                    ProductService.gI().sortByPriceDesc();
                    break;
            }
        }
    }
}
