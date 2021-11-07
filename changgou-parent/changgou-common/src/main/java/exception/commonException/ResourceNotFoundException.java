package exception.commonException;

public class ResourceNotFoundException extends RuntimeException{
    private Integer errorCode;

    public ResourceNotFoundException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
