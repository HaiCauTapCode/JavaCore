package ss5.exercise;

import java.util.Scanner;

public class ProductService {
    private final Scanner sc = new Scanner(System.in);
    private static ProductService instance;

    public static ProductService gI() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public boolean addSingleProduct() {
        System.out.print("Nhập Id: ");
        String id = sc.nextLine();

        for (int i = 0; i < ProductManager.count; i++) {
            if (ProductManager.storage[i].getId().equalsIgnoreCase(id)) {
                Logger.warning("Id đã tồn tại\n");
                return false;
            }
        }

        System.out.print("Nhập tên: ");
        String name = Util.formatName(sc.nextLine());

        if (name.isBlank()) {
            Logger.warning("Tên không được để trống\n");
            return false;
        }

        System.out.print("Giá: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        ProductManager.storage[ProductManager.count++] = new Product(id, name, price, quantity);

        Logger.success("Thêm sản phẩm thành công!\n");
        return true;
    }


    public void addProducts() {
        System.out.print("Nhập số lượng sản phẩm muốn thêm: ");
        int n = Integer.parseInt(sc.nextLine());

        if (isFullStorage(n)) {
            return;
        }

        int added = 0;

        while (added < n) {
            System.out.println("Nhập sản phẩm thứ " + (added + 1));

            if (addSingleProduct()) {
                added++;
            } else {
                Logger.warning("Vui lòng nhập lại\n");
            }

            if (ProductManager.count >= ProductManager.MAX_PRODUCT) {
                Logger.error("Kho đã đầy, không thể thêm nữa!\n");
                break;
            }
        }
    }

    public void showAll() {
        Logger.log(Logger.CYAN_BOLD_BRIGHT, "===== DANH SÁCH TẤT CẢ SẢN PHẨM TRONG KHO (HIỆN CÓ " + ProductManager.count + " SẢN PHẨM) =====\n");

        double total = 0;

        for (int i = 0; i < ProductManager.count; i++) {
            Logger.log(Logger.WHITE_BRIGHT, (i + 1) + ". ");
            ProductManager.storage[i].showInfo();
            total += ProductManager.storage[i].getTotalValue();
        }

        Logger.log(Logger.GREEN_BOLD_BRIGHT, "TỔNG GIÁ TRỊ TOÀN KHO = " + total + "\n");
    }


    public void findCheapest() {
        Logger.log(Logger.CYAN_BOLD_BRIGHT, "===== DANH SÁCH SẢN PHẨM RẺ NHẤT TRONG KHO =====\n");

        if (ProductManager.count == 0) {
            Logger.warning("Kho hiện đang trống!\n");
            return;
        }

        double minPrice = ProductManager.storage[0].getPrice();

        for (int i = 1; i < ProductManager.count; i++) {
            if (ProductManager.storage[i].getPrice() < minPrice) {
                minPrice = ProductManager.storage[i].getPrice();
            }
        }

        for (int i = 0; i < ProductManager.count; i++) {
            if (ProductManager.storage[i].getPrice() == minPrice) {
                ProductManager.storage[i].showInfo();
            }
        }
    }

    public void updateProduct() {
        System.out.print("Nhập ID hoặc tên gần đúng: ");
        String key = sc.nextLine().trim().toLowerCase();

        int foundCount = 0;
        int foundIndex = -1;

        for (int i = 0; i < ProductManager.count; i++) {
            String id = ProductManager.storage[i].getId().toLowerCase();
            String name = ProductManager.storage[i].getName().toLowerCase();

            if (id.equals(key) || name.contains(key)) {
                foundCount++;
                foundIndex = i;
            }
        }

        if (foundCount == 0) {
            Logger.warning("Không tìm thấy sản phẩm phù hợp!\n");
            return;
        }

        if (foundCount > 1) {
            Logger.warning("Có nhiều sản phẩm phù hợp. Vui lòng tìm chính xác hơn!\n");
            return;
        }

        Product p = ProductManager.storage[foundIndex];

        Logger.log(Logger.CYAN_BOLD_BRIGHT, "SẢN PHẨM ĐƯỢC CHỌN:\n");
        p.showInfo();

        System.out.print("Tên mới (Enter để bỏ qua): ");
        String newName = sc.nextLine();
        if (!newName.isBlank()) {
            p.setName(Util.formatName(newName));
        }

        System.out.print("Giá mới (Enter để bỏ qua): ");
        String priceInput = sc.nextLine();
        if (!priceInput.isBlank()) {
            double price = Double.parseDouble(priceInput);
            if (price >= 0) {
                p.setPrice(price);
            }
        }

        System.out.print("Số lượng mới (Enter để bỏ qua): ");
        String qtyInput = sc.nextLine();
        if (!qtyInput.isBlank()) {
            int qty = Integer.parseInt(qtyInput);
            if (qty >= 0) {
                p.setQuantity(qty);
            }
        }

        Logger.success("Cập nhật sản phẩm thành công!\n");
    }

    public void deleteProduct() {
        System.out.print("Nhập Id cần xóa: ");
        String id = sc.nextLine().trim();

        int foundIndex = -1;

        for (int i = 0; i < ProductManager.count; i++) {
            if (ProductManager.storage[i].getId().equalsIgnoreCase(id)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            Logger.warning("Không tìm thấy sản phẩm có id này!\n");
            return;
        }

        for (int i = foundIndex; i < ProductManager.count - 1; i++) {
            ProductManager.storage[i] = ProductManager.storage[i + 1];
        }

        ProductManager.storage[ProductManager.count - 1] = null;
        ProductManager.count--;

        Logger.success("Xóa sản phẩm có id " + id + " thành công\n");
    }


    public void sortByPriceDesc() {
        int n = ProductManager.count;

        if (n <= 1) {
            Logger.warning("Không có sản phẩm nào để sắp xếp!\n");
            return;
        }

        // Tạo mảng tạm
        Product[] temp = new Product[n];
        for (int i = 0; i < n; i++) {
            temp[i] = ProductManager.storage[i];
        }

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (temp[j].getPrice() < temp[j + 1].getPrice()) {
                    Product t = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = t;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        Logger.success("Danh sách sản phẩm theo giá giảm dần\n");

        for (int i = 0; i < n; i++) {
            temp[i].showInfo();
        }
    }


    public boolean isFullStorage(int n) {
        if (ProductManager.count >= ProductManager.MAX_PRODUCT) {
            System.out.println("Kho đã đầy!");
            return true;
        }

        if (ProductManager.count + n > ProductManager.MAX_PRODUCT) {
            System.out.println("Số lượng sản phẩm thêm vào đã vượt quá sức chứa của kho");
            return true;
        }

        return false;
    }
}
