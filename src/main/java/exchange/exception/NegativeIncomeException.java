package exchange.exception;

public class NegativeIncomeException extends RuntimeException {
    public NegativeIncomeException(String message){
        super(message);
    }
}
