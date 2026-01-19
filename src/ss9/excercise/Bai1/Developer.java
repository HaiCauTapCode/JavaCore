package ss9.excercise.Bai1;

public class Developer extends Employee {
    private String[] skills;

    public Developer(int id, String name, double baseSalary, String[] skills) {
        super(id, name, baseSalary);
        this.skills = skills;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public void doWork() {
        System.out.println("Developer is writing code...");
    }

    @Override
    public double calculateSalary() {
        return baseSalary * 1.5;
    }
}

