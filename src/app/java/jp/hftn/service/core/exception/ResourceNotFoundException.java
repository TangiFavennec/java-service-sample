package jp.hftn.service.core.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String reason) {
        super(reason);
    }
}
