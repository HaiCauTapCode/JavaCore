package ss9.excercise.Bai4;

import ss9.excercise.Bai1.Developer;
import ss9.excercise.Bai1.Tester;
import ss9.excercise.Bai2.CompanyManager;

public class Main {
    public static void main(String[] args) {

        CompanyManager manager = new CompanyManager(2, 3);

        manager.getCompany()[0][0] = new Developer(1, "A", 20_000_000, new String[]{"Java"});
        manager.getCompany()[0][1] = null;
        manager.getCompany()[0][2] = new Tester(2, "B", 15_000_000, 10);

        manager.processEmployees(e -> {
            try {
                System.out.println(e.getName() + " - " + e.calculateSalary());
            } catch (Exception ex) {
                System.out.println("Bỏ qua null...");
            }
        });
    }
}

