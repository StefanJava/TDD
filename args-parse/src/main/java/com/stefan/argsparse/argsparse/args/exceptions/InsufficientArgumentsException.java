package com.stefan.argsparse.argsparse.args.exceptions;

public class InsufficientArgumentsException extends RuntimeException {

    private final String option;

    public InsufficientArgumentsException(String value) {
        option = value;
    }

    public String getOption() {
        return option;
    }
}