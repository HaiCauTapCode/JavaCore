package ss3.practice.Bai4;

public class FactorialRecursive {

    public static long tinhGiaiThua(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return n * tinhGiaiThua(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        long result = tinhGiaiThua(n);

        System.out.println(n + "! = " + result);
    }
}

