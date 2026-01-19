package ss8.practice.Bai1;

import java.util.function.Predicate;
import java.util.Arrays;

public class TransactionManager {

    private final Transaction[] transactions;
    private int size = 0;

    public TransactionManager(int capacity) {
        transactions = new Transaction[capacity];
    }

    public void add(Transaction transaction) {
        if (size < transactions.length) {
            transactions[size++] = transaction;
        }
    }

    public Transaction[] filter(Predicate<Transaction> condition) {
        Transaction[] result = new Transaction[size];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (condition.test(transactions[i])) {
                result[count++] = transactions[i];
            }
        }

        return Arrays.copyOf(result, count);
    }

    public void calculateTaxForWithdraw(Taxable tax) {
        for (int i = 0; i < size; i++) {
            if (transactions[i] instanceof WithdrawTransaction) {
                double amount = transactions[i].getAmount();
                double taxValue = tax.calculateTax(amount);
                System.out.println("Withdraw ID " + transactions[i].getId()
                        + " | Amount: " + amount
                        + " | Tax: " + taxValue);
            }
        }
    }

    public void sortByAmountDesc() {
        for (int i = 1; i < size; i++) {
            double key = transactions[i].amount;
            int j = i - 1;

            while (j >= 0 && transactions[j].getAmount() < key) {
                transactions[j + 1] = transactions[j];
                j--;
            }

            transactions[j + 1].amount = key;
        }
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(transactions[i].getId() + " | " + transactions[i].getAmount());
        }
    }
}

