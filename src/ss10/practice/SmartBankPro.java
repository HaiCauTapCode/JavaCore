package ss10.practice;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SmartBankPro {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Set<String> citizenIds = new HashSet<>();

    private final AtomicInteger savSeq = new AtomicInteger(0);
    private final AtomicInteger creSeq = new AtomicInteger(0);

    public Map<String, Account> getAccounts() { return accounts; }

    private String nextAccountNumber(String type) {
        if (type.equalsIgnoreCase("SAV")) {
            int n = savSeq.incrementAndGet();
            return String.format("SAV-%03d", n);
        } else {
            int n = creSeq.incrementAndGet();
            return String.format("CRE-%03d", n);
        }
    }

    // Chức năng 1: mở tài khoản + check trùng citizenId
    public Account openAccount(String ownerName, String citizenId, String accountType, double initialMoney, Double creditLimit)
            throws DuplicateCustomerException {

        if (citizenIds.contains(citizenId)) {
            throw new DuplicateCustomerException("Lỗi: Khách hàng có ID " + citizenId + " đã tồn tại trong hệ thống. Không thể mở thêm!");
        }

        if (initialMoney < 0) {
            throw new IllegalArgumentException("Số tiền ban đầu không được âm.");
        }

        Account acccount;

        if (accountType.equalsIgnoreCase("SAVINGS")) {
            String accNo = nextAccountNumber("SAV");
            acccount = new SavingsAccount(accNo, ownerName, citizenId, initialMoney);
        } else if (accountType.equalsIgnoreCase("CREDIT")) {
            if (creditLimit == null || creditLimit <= 0) {
                throw new IllegalArgumentException("Hạn mức tín dụng phải > 0.");
            }
            String accNo = nextAccountNumber("CRE");
            acccount = new CreditAccount(accNo, ownerName, citizenId, initialMoney, creditLimit);
        } else {
            throw new IllegalArgumentException("Loại tài khoản không hợp lệ. Chỉ nhận SAVINGS hoặc CREDIT.");
        }

        citizenIds.add(citizenId);
        accounts.put(acccount.getAccountNumber(), acccount);
        return acccount;
    }

    // Helper: tìm tài khoản, không có thì ném exception
    public Account requireAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null) {
            throw new AccountNotFoundException("Lỗi: Không tìm thấy tài khoản có mã " + accNo);
        }
        return acc;
    }

    // Chức năng 2: nạp/rút (đa hình)
    public void deposit(String accNo, double amount) throws AccountNotFoundException {
        Account acc = requireAccount(accNo);
        acc.deposit(amount);
    }

    public void withdraw(String accNo, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account acc = requireAccount(accNo);
        acc.withdraw(amount);
    }

    // Chức năng 3: chuyển khoản nội bộ (safe transaction)
    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws AccountNotFoundException, InsufficientFundsException {

        Account from = requireAccount(fromAccNo);

        Account to = accounts.get(toAccNo);
        if (to == null) {
            throw new AccountNotFoundException("Lỗi: Không tìm thấy tài khoản nhận có mã " + toAccNo);
        }

        from.transfer(to, amount);
        System.out.println("Chuyển khoản thành công!");
        System.out.println(" - Tài khoản gửi (" + fromAccNo + "): " + from.getBalance());
        System.out.println(" - Tài khoản nhận (" + toAccNo + "): " + to.getBalance());
    }

    // Chức năng 4: Thống kê & lọc
    // 4.1: danh sách tài khoản đang nợ (balance < 0, thường là credit)
    public List<Account> listDebtAccounts() {
        return accounts.values().stream()
                .filter(a -> a.getBalance() < 0)
                .collect(Collectors.toList());
    }

    // 4.2: tài khoản số dư lớn nhất
    public Optional<Account> findMaxBalanceAccount() {
        return accounts.values().stream()
                .max(Comparator.comparingDouble(Account::getBalance));
    }

    // Menu đề ghi "VIP (Stream & Lambda)": lọc TK > threshold và in hoa tên
    public void printVipAccounts(double threshold) {
        System.out.println("--- DANH SÁCH TÀI KHOẢN VIP ---");
        List<Account> vip = accounts.values().stream()
                .filter(a -> a.getBalance() > threshold)
                .sorted(Comparator.comparingDouble(Account::getBalance).reversed())
                .toList();

        int i = 1;
        for (Account a : vip) {
            System.out.println(i++ + ". " + a.getOwnerName().toUpperCase() + " - Số dư: " + (long)a.getBalance());
        }
        System.out.println("-------------------------------");

        double total = accounts.values().stream().mapToDouble(Account::getBalance).sum();
        System.out.println("Tổng số vốn ngân hàng đang quản lý: " + (long)total);
    }

    // Chức năng 5: Chốt cuối tháng (Functional Interface + lambda)
    public void endOfMonth(InterestRate savingsFormula, InterestRate creditFeeFormula) {
        System.out.println("Đang quét danh sách tài khoản...");

        for (Account acc : accounts.values()) {
            if (acc instanceof SavingsAccount) {
                double delta = savingsFormula.apply(acc.getBalance());
                acc.setBalance(acc.getBalance() + delta);
                System.out.println("- Tài khoản " + acc.getAccountNumber() + ": +" + (long)delta
                        + " (Lãi tiết kiệm) -> Số dư mới: " + (long)acc.getBalance());
            } else if (acc instanceof CreditAccount) {
                // chỉ tính phí trên nợ (balance âm)
                if (acc.getBalance() < 0) {
                    double fee = creditFeeFormula.apply(acc.getBalance()); // nên ra số âm
                    acc.setBalance(acc.getBalance() + fee);               // cộng số âm => trừ
                    System.out.println("- Tài khoản " + acc.getAccountNumber() + ": " + (long)fee
                            + " (Phí quản lý) -> Số dư mới: " + (long)acc.getBalance());
                } else {
                    System.out.println("- Tài khoản " + acc.getAccountNumber() + ": 0 (Không nợ, không tính phí) -> Số dư: " + (long)acc.getBalance());
                }
            }
        }

        System.out.println("Hoàn tất quyết toán cuối tháng!");
    }

    // Dữ liệu mẫu để demo nhanh
    public void seedDemoData() {
        try {
            openAccount("Nguyen Van A", "001", "SAVINGS", 1000, null);           // SAV-001
            openAccount("Tran Thi B", "002", "CREDIT", 8000, 5000.0);           // CRE-001
            openAccount("Le Van C", "003", "SAVINGS", 15000, null);             // SAV-002

            // credit nợ để test lọc nợ
            Account cre = openAccount("Pham Thi D", "004", "CREDIT", 0, 5000.0);// CRE-002
            cre.withdraw(3000); // balance -3000

        } catch (Exception ignored) {}
    }
}

