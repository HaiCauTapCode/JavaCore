package ss4.exercise.Bai7;

public class Main {
    public static void main(String[] args) {

        String s1 = "t3h";
        String s2 = new String("t3h");

        if (s1 == s2) {
            System.out.println("s1 == s2 ? " + (s1 == s2));
        } else {
            System.out.println("s1 == s2 ? " + (s1 == s2));
        }

        if (s1.equals(s2)) {
            System.out.println("s1.equals(s2) ? " + s1.equals(s2));
        } else {
            System.out.println("s1.equals(s2) ? " + s1.equals(s2));
        }

        if ("JAVA".equalsIgnoreCase("java")) {
            System.out.println("JAVA và java giống nhau (không phân biệt hoa thường)");
        } else {
            System.out.println("JAVA và java khác nhau");
        }
    }

    /*
     * s1 == s2 ==> false
     * Toán tử "==" dùng để so sánh địa chỉ vùng nhớ
     * s1 được khai báo bằng literal đang trỏ tới chuỗi "t3h" trong string pool
     * s2 được tạo bằng từ khóa new nên trỏ tới một object mới trong heap
     * Hai object này nằm ở hai vùng nhớ khác nhau => địa chỉ khác nhau
     * nên s1 == s2 trả về false.
     *
     * s1.equals(s2)
     * Phương thức equals() dùng để so sánh nội dung của chuỗi.
     * Vì cả hai đều chứa "t3h" nên equals() trả về true.
     */
}

