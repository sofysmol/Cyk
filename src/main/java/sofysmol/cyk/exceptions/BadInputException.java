package sofysmol.cyk.exceptions;

/**
 * Created by sofysmo on 09.10.16.
 */
public class BadInputException extends RuntimeException{
    public BadInputException() {
        super();
    }

    public BadInputException(String s) {
        super(s);
    }
    public BadInputException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public BadInputException(Throwable throwable) {
        super(throwable);
    }
}
