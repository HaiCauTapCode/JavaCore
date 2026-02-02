package ss10.practice;

public interface IBankingAction {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
    void transfer(Account to, double amount) throws InsufficientFundsException;
}

