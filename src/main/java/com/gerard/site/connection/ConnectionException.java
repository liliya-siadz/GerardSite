package com.gerard.site.connection;

/**
 * Class is subclass of checked exception {@link Exception}
 * and indicates that was some connection errors with
 * database connection in the connection layer.
 * Mostly this exception should be thrown up .
 * Can be caught by next layer and covered by the next layer exception .
 */
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

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ConnectionException{"
                + "message: " + super.getMessage()
                + ", cause: " + super.getCause()
                + "}:";
    }
}
