package com.hexa.ums.exception;

public abstract class ConversionException extends RuntimeException {

    /**
     * Construct a new conversion exception.
     *
     * @param message the exception message
     */
    public ConversionException(String message) {
        super(message);
    }

    /**
     * Construct a new conversion exception.
     *
     * @param message the exception message
     * @param cause   the cause
     */
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

}