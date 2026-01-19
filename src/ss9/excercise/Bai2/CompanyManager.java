package ss9.excercise.Bai2;

import ss9.excercise.Bai1.Employee;

import java.util.function.Consumer;

public class CompanyManager {
    private Employee[][] company;

    public CompanyManager(int numDepartments, int maxEmployeesPerDept) {
        company = new Employee[numDepartments][maxEmployeesPerDept];
    }

    public Employee[][] getCompany() {
        return company;
    }

    public void processEmployees(Consumer<Employee> action) {
        for (Employee[] employees : company) {
            for (Employee employee : employees) {
                action.accept(employee);
            }
        }
    }
}
