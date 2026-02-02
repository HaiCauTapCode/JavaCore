package exam2;

public class FullTimeStudent extends Student {
    private static final double PRICE_PER_CREDIT = 300.0;

    public FullTimeStudent(String id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return "FULL_TIME";
    }

    @Override
    public double calculateTuitionFee() {
        int totalCredits = 0;
        for (Course c : enrolledCourses) {
            totalCredits += c.getCredits();
        }
        return totalCredits * PRICE_PER_CREDIT;
    }
}
