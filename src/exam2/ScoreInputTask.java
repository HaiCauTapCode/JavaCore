package exam2;

import java.util.*;

public class ScoreInputTask implements Runnable {
    private List<String> studentIds;
    private List<String> courseIds;
    private StudentManagement management;

    public ScoreInputTask(List<String> studentIds, List<String> courseIds, StudentManagement management) {
        this.studentIds = studentIds;
        this.courseIds = courseIds;
        this.management = management;
    }

    @Override
    public void run() {
        Random rd = new Random();
        for (String studentId : studentIds) {
            long st = System.currentTimeMillis();
            Student student = management.searchStudent(studentId).orElse(null);
            if (student == null) {
                continue;
            }

            for (Course c : student.getEnrolledCourses()) {
                double score = rd.nextDouble() * 10.0;
                score = Math.round(score * 100.0) / 100.0;
                try {
                    management.safeInputScore(studentId, c.getCourseId(), score);
                } catch (Exception e) {
                }
            }

            long time = 100 - (System.currentTimeMillis() - st);
            try {
                Thread.sleep(Math.max(10, time));
            } catch (InterruptedException ignored) {
            }
        }
    }
}

