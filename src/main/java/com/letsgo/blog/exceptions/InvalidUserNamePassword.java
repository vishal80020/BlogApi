package com.letsgo.blog.exceptions;

public class InvalidUserNamePassword extends RuntimeException {

    private String message;

    public InvalidUserNamePassword(String message) {
        super(String.format("%s",message));
        this.message = message;
    }

}
