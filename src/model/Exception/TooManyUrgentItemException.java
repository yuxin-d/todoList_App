package model.Exception;

public class TooManyUrgentItemException extends TodoListException {
    public TooManyUrgentItemException(String msg) {
        super(msg);
    }
}
