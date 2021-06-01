package com.formula.racing.Formula.One.Racing.domain.exception;

import java.text.MessageFormat;

public class TeamNotFoundException extends RuntimeException{

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param Id the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TeamNotFoundException(Long Id) {
        super(MessageFormat.format("Team with Team ID:  {} not found!", Id));
    }
}
