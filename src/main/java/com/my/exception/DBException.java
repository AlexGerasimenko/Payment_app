package com.my.exception;

/**
 * An exception that provides information on a database access error.
 *
 * @author
 */
public class DBException extends AppException {


    private static final long serialVersionUID = -6464629579011860424L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
