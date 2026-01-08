package ss4.exercise.Bai5;

public class Main {
    public static void main(String[] args) {
        String str = "Java,Python,C++,PHP,JavaScript";

        String[] parts = str.split(",");

        System.out.println("Các phần tử sau khi tách:");
        for (String s : parts) {
            System.out.println(s);
        }

        System.out.print("Các chuỗi bắt đầu bằng \"Java\": ");
        for (String part : parts) {
            if (part.startsWith("Java")) {
                System.out.print(part + " ");
            }
        }

        int index = str.indexOf('P');
        System.out.println("\nVị trí xuất hiện đầu tiên của ký tự 'P': " + index);
    }
}

