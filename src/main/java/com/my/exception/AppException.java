package com.my.exception;

/**
 * An exception that provides information on an application error.
 *
 * @author Herasimenko Olexandra
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 4172467996961794510L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

}
