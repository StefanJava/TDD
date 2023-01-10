package com.stefan.argsparse.argsparse.args;

public class InsufficientArgumentsException extends RuntimeException {

    private String option;

    public InsufficientArgumentsException(String value) {
        option = value;
    }

    public String getOption() {
        return option;
    }
}