package exceptions;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {}

    public EmailNotFoundException(String message) { super(message); }
}
