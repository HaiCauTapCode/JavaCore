package ss9.excercise.Bai2;

import ss9.excercise.Bai1.Developer;
import ss9.excercise.Bai1.Employee;
import ss9.excercise.Bai1.Tester;

import java.util.Random;
import java.util.Scanner;

import static ss9.excercise.Bai2.InvalidDataException.inputSalary;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = 2;
        int M = 3;
        Employee[][] company = new Employee[N][M];

        for (int i = 0; i < N; i++) {
            System.out.println("\n=== Nhập dữ liệu cho phòng ban " + (i + 1) + " ===");

            for (int j = 0; j < M; j++) {
                boolean valid = false;

                while (!valid) {
                    try {
                        System.out.print("Nhập lương nhân viên [" + i + "][" + j + "]: ");
                        double salary = sc.nextDouble();
                        double checkedSalary = inputSalary(salary);

                        if (i % 2 == 0) {
                            company[i][j] = new Developer(j, "Dev " + j, checkedSalary, new String[]{"Java", "Spring", "MySQL"});
                        } else {
                            company[i][j] = new Tester(j, "Tester " + j, checkedSalary, new Random().nextInt(100));
                        }
                        valid = true;
                    } catch (InvalidDataException e) {
                        System.out.println("Lỗi: " + e.getMessage() + " Vui lòng nhập lại!");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Bạn phải nhập số");
                        sc.nextLine();
                    } finally {
                        if (j == M - 1) {
                            printMemoryStatus(company, i);
                        }
                    }
                }
            }
        }
    }

    public static void printMemoryStatus(Employee[][] company, int deptIndex) {
        System.out.println("\nTrạng thái bộ nhớ phòng ban " + (deptIndex + 1) + ":");

        for (int i = 0; i < company[deptIndex].length; i++) {
            if (company[deptIndex][i] != null) {
                System.out.println(company[deptIndex][i].getClass().getSimpleName() + " " + i
                        + " | Lương cơ bản = " + company[deptIndex][i].getBaseSalary());
            }
        }
    }
}

