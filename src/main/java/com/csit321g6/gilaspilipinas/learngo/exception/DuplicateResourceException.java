package com.csit321g6.gilaspilipinas.learngo.exception;

/** Thrown for duplicates (email, course code, enrollment). Becomes HTTP 409. */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}