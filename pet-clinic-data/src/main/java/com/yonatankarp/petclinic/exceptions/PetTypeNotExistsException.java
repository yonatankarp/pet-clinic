package com.yonatankarp.petclinic.exceptions;

public class PetTypeNotExistsException extends RuntimeException {
    public PetTypeNotExistsException(final String message) {
        super(message);
    }
}
