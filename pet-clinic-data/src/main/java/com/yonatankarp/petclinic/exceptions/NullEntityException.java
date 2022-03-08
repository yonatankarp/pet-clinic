package com.yonatankarp.petclinic.exceptions;

public class NullEntityException extends RuntimeException {
    public NullEntityException() {
        super("Object cannot be null");
    }
}
