package com.gerard.site.exception;

/**
 * Subclass of checked exception {@link Exception},
 * indicates some problems at service layer
 * (for ex. problems inside business logic or jsp-view and etc.) .
 * <p>
 * Mostly this exception should be caught, then
 * inside {@code catch} block it's should be logged
 * and covered by RuntimeException {@link RuntimeException} .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
