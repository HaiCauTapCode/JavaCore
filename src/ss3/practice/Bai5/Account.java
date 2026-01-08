package ss3.practice.Bai5;

import java.util.Scanner;

public class Account {
    static String bankName = "T3H Bank";

    String customerName;
    double balance;

    public void showInfo() {
        System.out.println("Ngân hàng: " + bankName);
        System.out.println("Khách hàng: " + customerName);
        System.out.println("Số dư hiện tại: " + balance);
        System.out.println("---------------------------");
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Số tiền nạp phải lớn hơn 0!");
            return;
        }

        double newBalance = balance + amount;
        balance = newBalance;

        System.out.println("Nạp tiền thành công!");
    }

    public static double calculateCompoundInterest(double currentBalance, int months) {
        if (months == 0) {
            return currentBalance;
        }

        return calculateCompoundInterest(currentBalance * 1.01, months - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account = new Account();
        account.customerName = "Nguyễn Văn A";
        account.balance = 0;

        while (true) {
            System.out.print("Nhập số tiền cần nạp (Nhập 0 để thoát): ");
            double amount = scanner.nextDouble();
            if (amount == 0) {
                break;
            }

            account.deposit(amount);
            account.showInfo();
        }

        System.out.print("Nhập số tháng gửi tiết kiệm: ");
        int months = scanner.nextInt();

        double finalBalance = calculateCompoundInterest(account.balance, months);

        System.out.println("Số dư sau " + months + " tháng (lãi kép 1%): " + finalBalance);
    }
}

