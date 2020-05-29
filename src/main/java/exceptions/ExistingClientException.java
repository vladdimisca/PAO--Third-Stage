package exceptions;

public class ExistingClientException extends Exception {
    public ExistingClientException() {}

    public ExistingClientException(String message) { super(message); }
}
