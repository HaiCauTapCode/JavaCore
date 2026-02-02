package ss10.practice;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SmartBankPro bank = new SmartBankPro();

        // Demo dữ liệu sẵn để test nhanh
//        bank.seedDemoData();

        while (true) {
            printMenu();
            System.out.print("Lựa chọn của bạn: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": {
                    // Mở tài khoản mới
                    try {
                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Nhập CitizenID: ");
                        String cid = sc.nextLine().trim();

                        System.out.print("Chọn loại TK (1: Savings, 2: Credit): ");
                        String typeChoice = sc.nextLine().trim();

                        String type;
                        Double limit = null;

                        if (typeChoice.equals("1")) {
                            type = "SAVINGS";
                        } else if (typeChoice.equals("2")) {
                            type = "CREDIT";
                            System.out.print("Nhập hạn mức tín dụng: ");
                            limit = Double.parseDouble(sc.nextLine().trim());
                        } else {
                            System.out.println("Loại tài khoản không hợp lệ.");
                            break;
                        }

                        System.out.print("Nhập số tiền ban đầu: ");
                        double money = Double.parseDouble(sc.nextLine().trim());

                        Account acccount = bank.openAccount(name, cid, type, money, limit);
                        System.out.println("Mở tài khoản thành công cho khách hàng " + name + ". Số TK: " + acccount.getAccountNumber());
                    } catch (DuplicateCustomerException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi nhập số. Vui lòng nhập đúng định dạng.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Lỗi không xác định: " + e.getMessage());
                    }
                    break;
                }

                case "2": {
                    // Nạp/Rút
                    try {
                        System.out.print("Nhập số tài khoản: ");
                        String accNo = sc.nextLine().trim();

                        System.out.print("Chọn (1: Nạp, 2: Rút): ");
                        String t = sc.nextLine().trim();

                        System.out.print("Nhập số tiền: ");
                        double amount = Double.parseDouble(sc.nextLine().trim());

                        if (t.equals("1")) {
                            bank.deposit(accNo, amount);
                        } else if (t.equals("2")) {
                            bank.withdraw(accNo, amount);
                        } else {
                            System.out.println("Lựa chọn không hợp lệ.");
                        }
                    } catch (AccountNotFoundException | InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi nhập số.");
                    } catch (Exception e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;
                }

                case "3": {
                    // Chuyển khoản
                    try {
                        System.out.print("Từ TK: ");
                        String from = sc.nextLine().trim();

                        System.out.print("Sang TK: ");
                        String to = sc.nextLine().trim();

                        System.out.print("Số tiền: ");
                        double amount = Double.parseDouble(sc.nextLine().trim());

                        bank.transfer(from, to, amount);
                    } catch (AccountNotFoundException | InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi nhập số.");
                    } catch (Exception e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;
                }

                case "4": {
                    // VIP + thống kê
                    try {
                        bank.printVipAccounts(5000);

                        System.out.println();
                        System.out.println("Các tài khoản đang nợ (số dư âm):");
                        List<Account> debts = bank.listDebtAccounts();
                        if (debts.isEmpty()) {
                            System.out.println("(Không có)");
                        } else {
                            debts.forEach(a -> System.out.println(" - " + a));
                        }

                        Optional<Account> max = bank.findMaxBalanceAccount();
                        System.out.println();
                        if (max.isPresent()) {
                            System.out.println("Tài khoản có số dư lớn nhất: " + max.get());
                        } else {
                            System.out.println("Chưa có tài khoản nào.");
                        }

                    } catch (Exception e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;
                }

                case "5": {
                    // Chốt cuối tháng (lambda)
                    // Savings: + balance * 0.05
                    // Credit: trừ phí trên nợ: nợ * 0.01 (nợ là số âm => fee sẽ âm)
                    bank.endOfMonth(
                            (balance) -> balance * 0.05,
                            (balance) -> balance * 0.01
                    );
                    break;
                }

                case "0":
                    System.out.println("Thoát hệ thống. Tạm biệt!");
                    sc.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== SMARTBANK PRO MENU ==========");
        System.out.println("1. Mở tài khoản mới (Check trùng ID)");
        System.out.println("2. Nạp/Rút tiền (Xử lý Đa hình & Exception)");
        System.out.println("3. Chuyển khoản (Xử lý Map & Exception)");
        System.out.println("4. Danh sách khách hàng VIP (Stream & Lambda)");
        System.out.println("5. Chốt số dư cuối tháng (Functional Interface)");
        System.out.println("0. Thoát hệ thống");
        System.out.println("========================================");
    }
}

