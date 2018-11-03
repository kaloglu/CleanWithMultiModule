package com.kaloglu.domain.exception;

public class DefaultExceptionBundle implements ExceptionBundle {

    private static final String DEFAULT_MESSAGE = "Unknown exception";

    private final Exception mException;

    public DefaultExceptionBundle(Exception exception) {
        this.mException = exception;
    }

    @Override
    public Exception getException() {
        return this.mException;
    }

    @Override
    public String getMessage() {
        return this.mException != null ? this.mException.getMessage() : DEFAULT_MESSAGE;
    }
}
