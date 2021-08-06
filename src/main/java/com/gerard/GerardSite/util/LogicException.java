package com.gerard.GerardSite.util;

public class LogicException extends Exception {

    public LogicException() {
        super();
    }
    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "UtilException{"
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
