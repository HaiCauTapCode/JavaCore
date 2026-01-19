package ss8.practice.Bai1;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Withdraw " + amount + " from account (ID = " + id + ")");
    }
}

