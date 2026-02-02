package ss10.practice;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, String ownerName, String citizenId, double balance) {
        super(accountNumber, ownerName, citizenId, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền rút phải > 0.");
        }
        if (amount > balance) {
            double thieu = amount - balance;
            throw new InsufficientFundsException(
                    "Giao dịch thất bại: Tài khoản Tiết kiệm không được phép rút quá số dư (Thiếu " + thieu + ")"
            );
        }
        balance -= amount;
        System.out.println("Rút tiền thành công. Số dư mới: " + balance);
    }

    @Override
    public String getType() {
        return "SAVINGS";
    }
}

