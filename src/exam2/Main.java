package exam2;

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static StudentManagement management = new StudentManagement();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1:
                        management.initSampleData();
                        break;
                    case 2:
                        addStudentUI();
                        break;
                    case 3:
                        addCourseUI();
                        break;
                    case 4:
                        enrollUI();
                        break;
                    case 5:
                        inputScoreUI();
                        break;
                    case 6:
                        printScoreBoardUI();
                        break;
                    case 7:
                        searchUI();
                        break;
                    case 8:
                        filterAndSortUI();
                        break;
                    case 9:
                        tuitionUI();
                        break;
                    case 10:
                        multiThreadScoreUI();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Khởi tạo dữ liệu mẫu");
        System.out.println("2. Thêm sinh viên");
        System.out.println("3. Thêm khóa học");
        System.out.println("4. Đăng ký khóa học cho sinh viên");
        System.out.println("5. Nhập điểm cho sinh viên");
        System.out.println("6. Xem bảng điểm của 1 sinh viên");
        System.out.println("7. Tìm kiếm sinh viên");
        System.out.println("8. Lọc & sắp xếp sinh viên");
        System.out.println("9. Tính học phí sinh viên");
        System.out.println("10. Nhập điểm tự động bằng đa luồng");
        System.out.println("0. Thoát");
    }

    private static void addStudentUI() throws DuplicateStudentException {
        System.out.println("1) FULL_TIME  2) PART_TIME");
        int type = readInt("Type: ");
        String id = readLine("Student ID: ");
        String name = readLine("Name: ");
        String email = readLine("Email: ");

        Student st;
        if (type == 1) {
            st = new FullTimeStudent(id, name, email);
        } else {
            st = new PartTimeStudent(id, name, email);
        }

        management.addStudent(st);
        System.out.println("Added student: " + st + "succesfully");
    }

    private static void addCourseUI() throws DuplicateStudentException {
        String cid = readLine("Course ID: ");
        String cname = readLine("Course Name: ");
        int credits = readInt("Credits: ");
        Course c = new Course(cid, cname, credits);
        management.addCourse(c);
        System.out.println("Added course: " + c);
    }

    private static void enrollUI() throws StudentNotFoundException, CourseNotFoundException {
        String sid = readLine("Student ID: ");
        String cid = readLine("Course ID: ");
        management.enrollStudentToCourse(sid, cid);
        System.out.println("Enrolled successfully!");
    }

    private static void inputScoreUI()
            throws StudentNotFoundException, CourseNotFoundException, InvalidScoreException {
        String sid = readLine("Student ID: ");
        String cid = readLine("Course ID: ");
        double score = readDouble("Score (0-10): ");
        management.inputScore(sid, cid, score);
        System.out.println("Input score done!");
    }

    private static void printScoreBoardUI() throws StudentNotFoundException {
        String sid = readLine("Student ID: ");
        management.printStudentScoreBoard(sid);
    }

    private static void searchUI() {
        System.out.println("1) Search by ID  2) Search by name + minGPA");
        int ch = readInt("Choose: ");
        if (ch == 1) {
            String id = readLine("ID: ");
            Optional<Student> op = management.searchStudent(id);
            System.out.println(op.isPresent() ? "Found: " + op.get() : "Not found (Optional empty).");
        } else {
            String name = readLine("Name contains: ");
            double minGpa = readDouble("Min GPA: ");
            List<Student> list = management.searchStudent(name, minGpa);
            if (list.isEmpty()) System.out.println("No result.");
            else list.forEach(s -> System.out.println(s + ", GPA=" + String.format("%.2f", s.getGpa())));
        }
    }

    private static void filterAndSortUI() {
        System.out.println("1) Filter GPA > 8 (Lambda)  2) Sort GPA desc  3) Sort Name asc");
        int ch = readInt("Choose: ");
        if (ch == 1) {
            List<Student> list = management.filterStudents(s -> s.getGpa() > 8.0);
            if (list.isEmpty()) {
                System.out.println("No student with GPA > 8.");
            } else {
                list.forEach(s -> System.out.println(s + ", GPA=" + String.format("%.2f", s.getGpa())));
            }
        } else if (ch == 2) {
            List<Student> list = management.sortStudentsByGpaDesc();
            list.forEach(s -> System.out.println(s.getName() + " - GPA=" + String.format("%.2f", s.getGpa())));
        } else {
            List<Student> list = management.sortStudentsByNameAsc();
            list.forEach(s -> System.out.println(s.getName() + " - GPA=" + String.format("%.2f", s.getGpa())));
        }
    }

    private static void tuitionUI() {
        String sid = readLine("Student ID: ");
        Student st = management.searchStudent(sid).orElse(null);
        if (st == null) {
            System.out.println("Not found.");
            return;
        }
        double fee = management.calculateTuition(st);
        System.out.println("Role=" + st.getRole() + ", Tuition Fee = " + fee);
    }

    private static void multiThreadScoreUI() {
        List<String> studentIds = management.getAllStudentIds();
        List<String> courseIds = management.getAllCourseIds();

        if (studentIds.isEmpty() || courseIds.isEmpty()) {
            System.out.println("Please init sample data first (option 1) or add student/course.");
            return;
        }

        // chia làm 2 thread demo multi-thread + synchronized
        List<String> part1 = studentIds.subList(0, studentIds.size() / 2);
        List<String> part2 = studentIds.subList(studentIds.size() / 2, studentIds.size());

        Thread t1 = new Thread(new ScoreInputTask(part1, courseIds, management), "ScoreThread-1");
        Thread t2 = new Thread(new ScoreInputTask(part2, courseIds, management), "ScoreThread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {

        }

        System.out.println("Multi-thread score input done (thread-safe).");
    }

    private static String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please input an integer!");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please input a number!");
            }
        }
    }
}

