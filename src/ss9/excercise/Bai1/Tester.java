package ss9.excercise.Bai1;

public class Tester extends Employee {
    private int bugsFound;

    public Tester(int id, String name, double baseSalary, int bugsFound) {
        super(id, name, baseSalary);
        this.bugsFound = bugsFound;
    }

    public int getBugsFound() {
        return bugsFound;
    }

    public void setBugsFound(int bugsFound) {
        this.bugsFound = bugsFound;
    }

    @Override
    public void doWork() {
        System.out.println("Tester is finding bugs...");
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (bugsFound * 100_000);
    }
}

