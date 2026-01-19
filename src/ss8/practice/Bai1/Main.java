package ss8.practice.Bai1;

public class Main {
    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager(10);

        transactionManager.add(new DepositTransaction("T1", 500));
        transactionManager.add(new WithdrawTransaction("T2", 1500));
        transactionManager.add(new DepositTransaction("T3", 3000));
        transactionManager.add(new WithdrawTransaction("T4", 800));
        transactionManager.add(new WithdrawTransaction("T5", 2500));

        System.out.println("Transactions > 1000:");
        Transaction[] bigTransactions = transactionManager.filter(t -> t.getAmount() > 1000);
        for (Transaction bigTransaction : bigTransactions) {
            System.out.println(bigTransaction.getId() + " | " + bigTransaction.getAmount());
        }

        System.out.println("\nTax 10%:");
        transactionManager.calculateTaxForWithdraw(amount -> amount * 0.1);

        System.out.println("\nProgressive Tax:");
        transactionManager.calculateTaxForWithdraw(amount -> {
            if (amount <= 1000) {
                return amount * 0.05;
            } else {
                return amount * 0.15;
            }
        });

        System.out.println("\nBefore sorting:");
        transactionManager.printAll();

        System.out.println("\nAfter sorting (DESC):");
        transactionManager.sortByAmountDesc();
        transactionManager.printAll();
    }
}

