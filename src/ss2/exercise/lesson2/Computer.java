package ss2.exercise.lesson2;

public class Computer {

    public static int totalComputersCreated = 0;

    String name;

    public Computer(String name) {
        this.name = name;
        totalComputersCreated++;
    }

    public static void changeDeviceName(Computer computer, String newName) {
        computer.name = newName;
    }

    public static void main(String[] args) {

        Computer c1 = new Computer("Laptop Dell");
        Computer c2 = new Computer("PC Gaming");

        System.out.println("Tên ban đầu c1: " + c1.name);

        changeDeviceName(c1, "MacBook Pro");

        System.out.println("Tên sau khi đổi c1: " + c1.name);

        System.out.println("Tổng số Computer đã tạo: " + Computer.totalComputersCreated);
    }
}

