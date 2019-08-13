package controller.exception;

public class CommandException extends Exception{
    public CommandException(String s) {
        super(s);
    }

    public CommandException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
