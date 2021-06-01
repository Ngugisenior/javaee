package com.formula.racing.Formula.One.Racing.domain.exception;

public class AbstractException <T,ID>{


    public AbstractException() {
    }

    public AbstractException(ID id) {
    }

    public PersonNotFoundException personException;

    public PersonNotFoundException getPersonException() {
        return personException;
    }

    public void setPersonException(PersonNotFoundException personException) {
        this.personException = personException;
    }
}
