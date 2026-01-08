package ss4.exercise.Bai6;

public class Main {
    public static void main(String[] args) {

        String sentence = "Học viện công nghệ thông tin T3H";

        int index = sentence.indexOf("T3H");
        System.out.println("Vị trí của \"T3H\": " + index);

        String result = sentence.substring(index);
        System.out.println("Chuỗi được cắt ra: " + result);

        char ch = sentence.charAt(5);
        System.out.println("Ký tự tại vị trí index 5: " + ch);
    }
}

