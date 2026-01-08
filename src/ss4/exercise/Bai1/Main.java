package ss4.exercise.Bai1;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.print("Các phần tử trong mảng là: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.print("\nTổng số phần tử của mảng: " + arr.length);
    }
}
