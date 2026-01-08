package ss6.practice.Bai1;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new EmployeeOffice("NV01", "Nguyễn Văn A", "Hà Nội", "0123456789", 22);
        Employee e2 = new EmployeeTechnical("NV02", "Trần Văn B", "Đà Nẵng", "0987654321", 3);
        Employee e3 = new EmployeeSales("NV03", "Lê Thị C", "TP.HCM", "0911222333", 100_000_000);

        System.out.println("=== Nhân viên văn phòng ===");
        e1.displayInfo();

        System.out.println("=== Nhân viên kỹ thuật ===");
        e2.displayInfo();

        System.out.println("=== Nhân viên bán hàng ===");
        e3.displayInfo();
    }
}
