package exam.service;

import exam.manager.ProductManager;
import exam.model.Product;

import java.util.Scanner;

public class Service {
    private final Scanner sc = new Scanner(System.in);
    private static Service instance;

    public static Service gI() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void addProduct() {
        System.out.print("Nhập categoryId (0-4): ");
        int categoryId = Integer.parseInt(sc.nextLine());

        if (categoryId < 0 || categoryId > ProductManager.TOTAL_CATEGORIES - 1) {
            System.out.println("CategoryId không hợp lệ!");
            return;
        }

        System.out.print("Nhập Id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        Product product = new Product(id, name, price, quantity);

        String msg = addProduct(categoryId, product);
        System.out.println(msg);
    }

    public String addProduct(int categoryId, Product product) {
        StringBuilder sb = new StringBuilder();

        if (ProductManager.counts[categoryId] == 20) {
            sb.append("Danh mục đã đầy, không thể thêm sản phẩm");
            return sb.toString();
        }

        for (int i = 0; i < ProductManager.counts[categoryId]; i++) {
            if (ProductManager.data[categoryId][i].equals(product)) {
                sb.append("Sản phẩm đã tồn tại trong danh mục");
                return sb.toString();
            }
        }

        ProductManager.data[categoryId][ProductManager.counts[categoryId]] = product;
        ProductManager.counts[categoryId]++;

        sb.append("Đã thêm sản phẩm ").append(product.getName())
                .append(" vào danh mục ").append(categoryId);

        return sb.toString();
    }

    public void showAll() {
        boolean hasProduct = false;

        System.out.println("===== DANH SÁCH SẢN PHẨM TRONG KHO =====");

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            if (ProductManager.counts[i] > 0) {
                System.out.println("Danh mục " + i + ":");
                for (int j = 0; j < ProductManager.counts[i]; j++) {
                    System.out.println(ProductManager.data[i][j]);
                    hasProduct = true;
                }
            }
        }

        if (!hasProduct) {
            System.out.println("Hiện không có sản phẩm nào trong kho!");
        }
    }

    public void searchByName() {
        System.out.print("Nhập từ khóa cần tìm: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            for (int j = 0; j < ProductManager.counts[i]; j++) {
                Product product = ProductManager.data[i][j];

                if (product.getName().toLowerCase().contains(keyword)) {
                    System.out.println(product);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm phù hợp");
        }
    }

    public void deleteById() {
        System.out.print("Nhập Id sản phẩm cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            for (int j = 0; j < ProductManager.counts[i]; j++) {
                if (ProductManager.data[i][j].getId() == id) {

                    // dịch mảng sang trái
                    for (int k = j; k < ProductManager.counts[i] - 1; k++) {
                        ProductManager.data[i][k] = ProductManager.data[i][k + 1];
                    }

                    // Xóa phần tử cuối (bị lặp)
                    ProductManager.data[i][ProductManager.counts[i] - 1] = null;
                    ProductManager.counts[i]--;

                    System.out.println("Đã xóa sản phẩm có ID = " + id);
                    return;
                }
            }
        }

        System.out.println("Không tìm thấy sản phẩm cần xóa");
    }

    public Product[] getAllProducts() {
        int total = 0;

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            total += ProductManager.counts[i];
        }

        Product[] arr = new Product[total];
        int index = 0;

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            for (int j = 0; j < ProductManager.counts[i]; j++) {
                arr[index++] = ProductManager.data[i][j];
            }
        }

        return arr;
    }

    public void quickSortByPriceDesc(Product[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        double pivot = arr[(left + right) / 2].getPrice();
        int i = left;
        int j = right;

        while (i <= j) {
            while (arr[i].getPrice() > pivot) {
                i++;
            }
            while (arr[j].getPrice() < pivot) {
                j--;
            }
            if (i <= j) {
                Product temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        quickSortByPriceDesc(arr, left, j);
        quickSortByPriceDesc(arr, i, right);
    }

    public void showSortedByPriceDesc() {
        Product[] ProductList = getAllProducts();

        if (ProductList.length == 0) {
            System.out.println("Kho đang trống!");
            return;
        }

        quickSortByPriceDesc(ProductList, 0, ProductList.length - 1);

        System.out.println("===== DANH SÁCH SẢN PHẨM (GIÁ GIẢM DẦN) =====");
        for (Product product : ProductList) {
            System.out.println(product);
        }
    }

    public void statistic() {
        double[] totals = new double[ProductManager.TOTAL_CATEGORIES];

        double maxValue = 0;
        int maxCategory = 0;

        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            double sum = 0;

            for (int j = 0; j < ProductManager.counts[i]; j++) {
                Product p = ProductManager.data[i][j];
                sum += p.getPrice() * p.getQuantity();
            }

            totals[i] = sum;

            if (sum > maxValue) {
                maxValue = sum;
                maxCategory = i;
            }
        }

        System.out.println("Danh mục | Tổng giá trị");
        for (int i = 0; i < ProductManager.TOTAL_CATEGORIES; i++) {
            System.out.println(i + "        | " + (long) totals[i]);
        }

        System.out.println("Danh mục có giá trị lớn nhất: " + maxCategory);
    }

}

