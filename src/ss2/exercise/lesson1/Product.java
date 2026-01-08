package ss2.exercise.lesson1;

public class Product {

    String productName;
    double price;
    int quantity;

    public static void main(String[] args) {

        Product product = new Product();
        product.productName = "Bàn phím cơ";
        product.price = 500_000;
        product.quantity = 12;

        double discount;

        discount = product.quantity > 10 ? 0.1 : 0.05;

        double total = (product.price * product.quantity) * (1 - discount);

        System.out.println("Sản phẩm " + product.productName + " - Tổng tiền: " + total);
    }
}

