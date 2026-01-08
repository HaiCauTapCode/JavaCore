package ss6.practice.Bai1;

public class EmployeeTechnical extends Employee {
    private int completedProjects;
    private static final double SALARY_PER_PROJECT = 5_000_000;

    public EmployeeTechnical(String employeeId, String fullName, String address, String phoneNumber, int completedProjects) {
        super(employeeId, fullName, address, phoneNumber);
        this.completedProjects = completedProjects;
    }

    @Override
    public double calculateSalary() {
        return completedProjects * SALARY_PER_PROJECT;
    }
}

