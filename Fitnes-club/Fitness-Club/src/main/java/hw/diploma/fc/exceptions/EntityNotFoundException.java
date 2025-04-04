package hw.diploma.fc.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
