package ss9.excercise.Bai3;

import ss9.excercise.Bai1.Developer;
import ss9.excercise.Bai1.Employee;
import ss9.excercise.Bai1.Tester;

import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Developer dev = new Developer(1, "Khánh", 15_000_000, new String[]{"Java", "Spring"});
        Tester tester = new Tester(2, "Minh", 18_000_000, 20);

        // Thưởng Tết
        SalaryBonus tetBonus = (Employee e) -> e.calculateSalary() * 0.1;

        // Thưởng dự án
        SalaryBonus projectBonus = (Employee e) -> 5_000_000;

        System.out.println("Thưởng Tết Dev: " + tetBonus.calculate(dev));
        System.out.println("Thưởng dự án Dev: " + projectBonus.calculate(dev));

        System.out.println("Thưởng Tết Tester: " + tetBonus.calculate(tester));
        System.out.println("Thưởng dự án Tester: " + projectBonus.calculate(tester));

        Employee[] employees = {dev, tester};

        Predicate<Employee> highSalary = e -> e.calculateSalary() > 20_000_000;
        System.out.println("\nNhân viên có lương > 20 triệu:");
        for (Employee e : employees) {
            if (highSalary.test(e)) {
                System.out.println(e.getName() + " - " + e.calculateSalary());
            }
        }

        Predicate<Employee> javaDeveloper = e -> e instanceof Developer &&
                Arrays.asList(((Developer) e).getSkills()).contains("Java");
        System.out.println("\nDeveloper có kỹ năng Java:");
        for (Employee e : employees) {
            if (javaDeveloper.test(e)) {
                System.out.println(e.getName());
            }
        }
    }
}

