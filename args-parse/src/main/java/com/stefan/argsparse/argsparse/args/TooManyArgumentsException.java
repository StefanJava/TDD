package com.stefan.argsparse.argsparse.args;

public class TooManyArgumentsException extends Exception {

    private String option;

    public TooManyArgumentsException(String value) {
        option = value;
    }

    public String getOption() {
        return option;
    }
}
