package ss4.exercise.Bai12;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập chuỗi: ");
        String input = sc.nextLine();

        if (isPalindrome(input)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Không phải Palindrome");
        }
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

}

