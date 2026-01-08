package ss6.practice.Bai1;

public class Employee {
    protected String id;
    protected String fullName;
    protected String address;
    protected String phoneNumber;

    public Employee(String id, String fullName, String address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public double calculateSalary() {
        return 0.0;
    }

    public void displayInfo() {
        System.out.println("Mã NV: " + id);
        System.out.println("Tên: " + fullName);
        System.out.println("Địa chỉ: " + address);
        System.out.println("SĐT: " + phoneNumber);
        System.out.println("Lương: " + calculateSalary());
    }
}

