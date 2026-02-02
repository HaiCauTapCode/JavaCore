package exam2;

import java.util.*;

public abstract class Student extends Person {
    protected double gpa;
    protected List<Course> enrolledCourses;

    public Student(String id, String name, String email) {
        super(id, name, email);
        this.gpa = 0.0;
        this.enrolledCourses = new ArrayList<>();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        if (course == null) {
            return;
        }
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    public void removeCourse(Course course) {
        enrolledCourses.remove(course);
    }

    // Tính GPA dựa trên điểm của các môn đã có trong scores
    public double calculateAverageScore(Map<Course, Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0;

        for (Map.Entry<Course, Double> e : scores.entrySet()) {
            Double sc = e.getValue();
            if (sc != null) {
                sum += sc;
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    //  mỗi loại sinh viên tính học phí khác nhau
    public abstract double calculateTuitionFee();
}

