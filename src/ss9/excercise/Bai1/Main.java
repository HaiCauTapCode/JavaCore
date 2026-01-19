package ss9.excercise.Bai1;

public class Main {
    public static void main(String[] args) {
        String[] devSkills = {"Java", "Spring", "MySQL"};
        Developer dev = new Developer(1, "Khánh", 10_000_000, devSkills);

        Tester tester = new Tester(2, "Minh", 8_000_000, 15);

        dev.doWork();
        System.out.println("Developer Salary: " + dev.calculateSalary());

        tester.doWork();
        System.out.println("Tester Salary: " + tester.calculateSalary());
    }
}

