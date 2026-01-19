package ss8.practice.Bai1;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Deposit " + amount + " into account (ID = " + id + ")");
    }
}

