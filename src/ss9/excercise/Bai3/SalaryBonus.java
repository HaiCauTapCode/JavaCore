package ss9.excercise.Bai3;

import ss9.excercise.Bai1.Employee;

@FunctionalInterface
public interface SalaryBonus {
    double calculate(Employee e);
}


