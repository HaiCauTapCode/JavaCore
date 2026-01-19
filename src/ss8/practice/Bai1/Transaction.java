package ss8.practice.Bai1;

public abstract class Transaction {

    protected String id;
    protected double amount;

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void process();
}

