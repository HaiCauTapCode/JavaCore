package ss3.exercise.lession4;

public class Employee {

    String name;
    double netSalary;

    public void calculateNetSalary(double grossSalary) {

        if (grossSalary < 0) {
            System.out.println("Lỗi: Lương net không được là số âm!");
            return;
        }

        double taxRate;
        double taxAmount;

        if (grossSalary > 20_000_000) {
            taxRate = 0.2;
        } else if (grossSalary >= 10_000_000) {
            taxRate = 0.1;
        } else {
            taxRate = 0.0;
        }

        taxAmount = grossSalary * taxRate;

        grossSalary -= taxAmount;
        netSalary = grossSalary;

        System.out.println("===== BẢNG LƯƠNG NHÂN VIÊN =====");
        System.out.println("Tên nhân viên: " + name);
        System.out.println("Lương net: " + (netSalary + taxAmount));
        System.out.println("Thuế phải đóng: " + taxAmount);
        System.out.println("Lương thực nhận: " + netSalary);
    }

    public static void main(String[] args) {

        Employee emp = new Employee();
        emp.name = "Nguyễn Văn A";

        emp.calculateNetSalary(25_000_000);
    }
}

