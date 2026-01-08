package ss3.practice.Bai1;

public class LeapYearChecker {

    public static void checkLeapYear(int year) {

        if (year % 400 == 0) {
            System.out.println(year + " là năm nhuận");
        } else if (year % 4 == 0 && year % 100 != 0) {
            System.out.println(year + " là năm nhuận");
        } else {
            System.out.println(year + " là năm thường");
        }
    }

    public static void main(String[] args) {
        checkLeapYear(2024);
        checkLeapYear(1900);
        checkLeapYear(2000);
    }
}

