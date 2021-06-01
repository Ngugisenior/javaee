package com.formula.racing.Formula.One.Racing.domain.exception;

import java.text.MessageFormat;

public class PartnerNotFoundException extends  RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param Id the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PartnerNotFoundException(Long Id) {
        super(MessageFormat.format("Partner with ID: {0} not found!", Id));
    }
}
