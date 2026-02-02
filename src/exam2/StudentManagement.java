package exam2;

import java.util.*;
import java.util.stream.Collectors;

public class StudentManagement {

    private Repository<Student> studentRepository = new Repository<>();
    private Repository<Course> courseRepository = new Repository<>();

    // studentId -> (courseId -> score)
    private Map<String, Map<String, Double>> scoreBoard = new HashMap<>();

    public Repository<Student> getStudentRepository() {
        return studentRepository;
    }

    public Repository<Course> getCourseRepository() {
        return courseRepository;
    }

    public void initSampleData() {
        // 5 courses
        addCourseNoThrow(new Course("C001", "Java Core", 3));
        addCourseNoThrow(new Course("C002", "OOP", 3));
        addCourseNoThrow(new Course("C003", "Database", 2));
        addCourseNoThrow(new Course("C004", "Data Structure", 3));
        addCourseNoThrow(new Course("C005", "Threading", 2));

        // 10 students (5 full, 5 part)
        for (int i = 1; i <= 5; i++) {
            Student s = new FullTimeStudent("FT" + i, "Full Student " + i, "ft" + i + "@mail.com");
            addStudentNoThrow(s);
        }
        for (int i = 1; i <= 5; i++) {
            Student s = new PartTimeStudent("PT" + i, "Part Student " + i, "pt" + i + "@mail.com");
            addStudentNoThrow(s);
        }

        // enroll random 2-3 courses each
        List<Course> allCourses = courseRepository.findAll();
        Random rd = new Random();

        for (Student st : studentRepository.findAll()) {
            int num = 2 + rd.nextInt(2); // 2-3
            Collections.shuffle(allCourses);
            for (int i = 0; i < num; i++) {
                st.enrollCourse(allCourses.get(i));
            }
        }

        System.out.println("Init sample data done!");
    }

    private void addStudentNoThrow(Student s) {
        try {
            addStudent(s);
        } catch (Exception ignored) {
        }
    }

    private void addCourseNoThrow(Course c) {
        try {
            addCourse(c);
        } catch (Exception ignored) {
        }
    }

    public void addStudent(Student student) throws DuplicateStudentException {
        if (studentRepository.exists(student.getId())) {
            throw new DuplicateStudentException("Student ID already exists: " + student.getId());
        }
        studentRepository.add(student.getId(), student);
    }

    public void addCourse(Course course) throws DuplicateStudentException {
        if (courseRepository.exists(course.getCourseId())) {
            throw new DuplicateStudentException("Course ID already exists: " + course.getCourseId());
        }
        courseRepository.add(course.getCourseId(), course);
    }

    public void enrollStudentToCourse(String studentId, String courseId)
            throws StudentNotFoundException, CourseNotFoundException {

        Student st = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + studentId));

        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + courseId));

        // already enrolled -> skip
        st.enrollCourse(c);
    }

    public void inputScore(String studentId, String courseId, double score)
            throws StudentNotFoundException, CourseNotFoundException, InvalidScoreException {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + studentId));

        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + courseId));

        if (score < 0.0 || score > 10.0) {
            throw new InvalidScoreException("Score must be in range 0-10");
        }

        // lưu score theo studentId -> courseId
        scoreBoard.putIfAbsent(studentId, new HashMap<>());
        scoreBoard.get(studentId).put(courseId, score);

        // cập nhật GPA của student dựa trên điểm hiện có
        updateStudentGpa(studentId);
    }

    // synchronized thread-safe
    public synchronized void safeInputScore(String studentId, String courseId, double score)
            throws StudentNotFoundException, CourseNotFoundException, InvalidScoreException {
        inputScore(studentId, courseId, score);
    }

    private void updateStudentGpa(String studentId) throws StudentNotFoundException {
        Student st = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + studentId));

        Map<String, Double> m = scoreBoard.get(studentId);
        if (m == null || m.isEmpty()) {
            st.setGpa(0.0);
            return;
        }

        double sum = 0.0;
        int count = 0;
        for (Double v : m.values()) {
            if (v != null) {
                sum += v;
                count++;
            }
        }
        st.setGpa(count == 0 ? 0.0 : sum / count);
    }

    public void printStudentScoreBoard(String studentId) throws StudentNotFoundException {
        Student st = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + studentId));

        System.out.println("===== SCORE BOARD =====");
        System.out.println(st);
        Map<String, Double> m = scoreBoard.get(studentId);

        if (m == null || m.isEmpty()) {
            System.out.println("No scores yet.");
            System.out.println("GPA = " + st.getGpa());
            return;
        }

        for (Map.Entry<String, Double> e : m.entrySet()) {
            String courseId = e.getKey();
            Double score = e.getValue();
            Course c = courseRepository.findById(courseId).orElse(null);
            String courseName = (c == null) ? "Unknown" : c.getCourseName();
            System.out.println(courseId + " - " + courseName + " : " + score);
        }
        System.out.println("GPA = " + String.format("%.2f", st.getGpa()));
    }

    public Optional<Student> searchStudent(String id) {
        return studentRepository.findById(id);
    }

    public List<Student> searchStudent(String name, double minGpa) {
        String keyword = (name == null) ? "" : name.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : studentRepository.findAll()) {
            if (s.getName().toLowerCase().contains(keyword) && s.getGpa() >= minGpa) {
                result.add(s);
            }
        }
        return result;
    }

    public double calculateTuition(Student student) {
        return student.calculateTuitionFee(); // Polymorphism
    }

    // filter using lambda
    public List<Student> filterStudents(StudentFilter filter) {
        List<Student> result = new ArrayList<>();
        for (Student s : studentRepository.findAll()) {
            if (filter.filter(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> sortStudentsByGpaDesc() {
        List<Student> list = studentRepository.findAll();
        list.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        return list;
    }

    public List<Student> sortStudentsByNameAsc() {
        List<Student> list = studentRepository.findAll();
        list.sort(Comparator.comparing(Student::getName));
        return list;
    }

    public List<String> getAllStudentIds() {
        return studentRepository.findAll().stream().map(Student::getId).collect(Collectors.toList());
    }

    public List<String> getAllCourseIds() {
        return courseRepository.findAll().stream().map(Course::getCourseId).collect(Collectors.toList());
    }
}

