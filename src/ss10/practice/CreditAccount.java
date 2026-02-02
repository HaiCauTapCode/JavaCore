package ss10.practice;

public class CreditAccount extends Account {
    private double creditLimit; // hạn mức

    public CreditAccount(String accountNumber, String ownerName, String citizenId, double balance, double creditLimit) {
        super(accountNumber, ownerName, citizenId, balance);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() { return creditLimit; }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền rút phải > 0.");
        }

        // Credit cho phép âm nhưng không vượt quá - creditLimit
        double newBalance = balance - amount;
        if (newBalance < -creditLimit) {
            double vuot = (-creditLimit) - newBalance; // số vượt quá hạn mức
            throw new InsufficientFundsException(
                    "Giao dịch thất bại: Vượt hạn mức tín dụng (Vượt " + vuot + "). Hạn mức: " + creditLimit
            );
        }
        balance = newBalance;
        System.out.println("Rút tiền thành công. Số dư mới: " + balance);
    }

    @Override
    public String getType() {
        return "CREDIT";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Limit: %.2f", creditLimit);
    }
}

