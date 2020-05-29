package exceptions;

public class OutOfSeatsException extends Exception {
    public OutOfSeatsException() {}

    public OutOfSeatsException(String message) { super(message); }
}
