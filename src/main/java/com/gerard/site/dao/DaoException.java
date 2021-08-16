package com.gerard.site.dao;

public class DaoException extends Exception{

    DaoException() {
        super();
    }

    DaoException(String message) {
        super(message);
    }

    DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    DaoException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "DaoException{"
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
