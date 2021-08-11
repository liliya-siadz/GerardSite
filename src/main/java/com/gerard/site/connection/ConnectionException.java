package com.gerard.site.connection;

public class ConnectionException extends Exception {
    ConnectionException() {
        super();
    }

    ConnectionException(String message) {
        super(message);
    }

    ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    ConnectionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "ConnectionException{"
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
