package com.gerard.site.service;

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

    @Override
    public String toString() {
        return "ServiceException{"
                + "message: " + super.getMessage() + ", cause: " + super.getCause()
                + "}:";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
