package ss10.practice;

public abstract class Account implements IBankingAction {
    protected String accountNumber;
    protected String ownerName;
    protected String citizenId;
    protected double balance;

    public Account(String accountNumber, String ownerName, String citizenId, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.citizenId = citizenId;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public String getCitizenId() { return citizenId; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Số tiền nạp phải > 0.");
            return;
        }
        balance += amount;
        System.out.println("Nạp tiền thành công. Số dư mới: " + balance);
    }

    @Override
    public void transfer(Account to, double amount) throws InsufficientFundsException {
        if (to == null) {
            throw new IllegalArgumentException("Tài khoản nhận không hợp lệ.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền chuyển phải > 0.");
        }

        // Safe transaction: rút thành công rồi mới nạp
        this.withdraw(amount);
        to.deposit(amount);
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("%s | %s | ID:%s | Số dư: %.2f",
                accountNumber, ownerName, citizenId, balance);
    }
}

