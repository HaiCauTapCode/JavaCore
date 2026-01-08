package ss6.practice.Bai1;

public class EmployeeSales extends Employee {
    private double salesAmount;
    private static final double COMMISSION_RATE = 0.1; // 10%

    public EmployeeSales(String employeeId, String fullName, String address, String phoneNumber, double salesAmount) {
        super(employeeId, fullName, address, phoneNumber);
        this.salesAmount = salesAmount;
    }

    @Override
    public double calculateSalary() {
        return salesAmount * COMMISSION_RATE;
    }
}

