package ss4.exercise.Bai10;

public class Main {
    public static void main(String[] args) {

        String s = "";

        for (int i = 0; i < 100000; i++) {
            s = s + "A";
        }

        System.out.println(s.length());
    }

    /*
     * String trong Java là immutable (bất biến).
     * Vì khi viết: s = s + "A"; thì thực chất Java thực hiện:
     * String temp = new StringBuilder(s).append("A").toString();
     * s = temp;
     *
     * Khi vòng lặp chạy 10000 lần, nó sẽ tạo ra:
     *  - 10000 đối tượng StringBuilder tạm
     *  - 10000 đối tượng String mới
     *
     * => Trong thời gian ngắn tạo ra rất nhiều object trong heap
     * sẽ làm ram và cpu tăng đột ngột gây chậm chương trình
     * => cách fix (ram và cpu tăng đột ngột do string)
     * sử dụng StringBuilder (single-thread) hoặc StringBuffer (multi-thread) để nối chuỗi
     */
}

