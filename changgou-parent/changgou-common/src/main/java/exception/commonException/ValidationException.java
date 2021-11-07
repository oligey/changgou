package exception.commonException;

public class ValidationException extends RuntimeException {

    private Integer errorCode;

    public ValidationException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
