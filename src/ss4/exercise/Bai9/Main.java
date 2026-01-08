package ss4.exercise.Bai9;

public class Main {

    public static boolean checkName(String name) {
        return "T3H".equals(name);
    }

    public static void main(String[] args) {
        System.out.println(checkName("T3H"));    // true
        System.out.println(checkName("t3h"));    // false
        System.out.println(checkName(null));     // false (không lỗi runtime)
    }

    /*
    * tại sao cách viết "T3H".equals(name) lại an toàn hơn name.equals("T3H")
    * vì "T3H" không bao giờ null nhưng biến name có thể null
    * */
}
