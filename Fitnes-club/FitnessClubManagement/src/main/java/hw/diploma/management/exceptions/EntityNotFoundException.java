package hw.diploma.management.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
