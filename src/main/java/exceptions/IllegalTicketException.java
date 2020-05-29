package exceptions;

public class IllegalTicketException extends Exception{
    public IllegalTicketException() {}

    public IllegalTicketException(String message) { super(message); }
}
