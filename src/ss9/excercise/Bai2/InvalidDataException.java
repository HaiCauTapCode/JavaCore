package ss9.excercise.Bai2;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }

    public static double inputSalary(double salary) throws InvalidDataException {
        if (salary <= 0) {
            throw new InvalidDataException("Lương phải lớn hơn 0!");
        }
        return salary;
    }

}

