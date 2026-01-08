package ss5.practice.Bai2;

public class Main {
    public static void main(String[] args) {
        Product[] products = new Product[100];

        products[0] = new Product("A1", " tivi ", 5000, 2);
        products[1] = new Product("A2", " chuot ", 200, 10);

        System.out.println("Tên chuẩn hóa: " + products[0].getName() + ", " + products[1].getName());

        Product cheapest = findCheapest(products);
        System.out.printf("Sản phẩm rẻ nhất: %s (%.1f)%n",
                cheapest.getName(), cheapest.getPrice());

        double total = calculateTotalValue(products);
        System.out.println("Tổng giá trị kho: " + total);
    }

    public static Product findCheapest(Product[] list) {
        Product cheapest = null;

        for (Product product : list) {
            if (product != null) {
                if (cheapest == null || product.getPrice() < cheapest.getPrice()) {
                    cheapest = product;
                }
            }
        }

        return cheapest;
    }

    public static double calculateTotalValue(Product[] list) {
        double total = 0;

        for (Product product : list) {
            if (product != null) {
                total += product.getPrice() * product.getQuantity();
            }
        }

        return total;
    }
}

