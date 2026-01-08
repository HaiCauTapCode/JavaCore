package ss6.practice.Bai1;

public class EmployeeOffice extends Employee {
    private int workingDays;
    private static final double SALARY_PER_DAY = 200_000;

    public EmployeeOffice(String employeeId, String fullName, String address, String phoneNumber, int workingDays) {
        super(employeeId, fullName, address, phoneNumber);
        this.workingDays = workingDays;
    }

    @Override
    public double calculateSalary() {
        return workingDays * SALARY_PER_DAY;
    }
}

