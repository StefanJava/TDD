package com.stefan.argsparse.argsparse.args.exceptions;

public class TooManyArgumentsException extends RuntimeException {

    private final String option;

    public TooManyArgumentsException(String value) {
        option = value;
    }

    public String getOption() {
        return option;
    }
}
