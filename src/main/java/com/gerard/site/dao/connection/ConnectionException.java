package com.gerard.site.dao.connection;

/**
 * Subclass of checked exception {@link Exception},
 * indicates some connection problems with
 * database connection at the connection layer.
 * Mostly this exception should be thrown up .
 * Can be caught by next layer and covered by the next layer exception .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
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
}
