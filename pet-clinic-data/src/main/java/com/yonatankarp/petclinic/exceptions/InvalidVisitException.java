package com.yonatankarp.petclinic.exceptions;

public class InvalidVisitException extends RuntimeException{
    public InvalidVisitException() {
        super("Invalid visit, pet or owner are not persist");
    }
}
