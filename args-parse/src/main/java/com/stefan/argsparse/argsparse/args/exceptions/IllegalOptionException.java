package com.stefan.argsparse.argsparse.args.exceptions;

public class IllegalOptionException extends RuntimeException {

    private final String parameter;

    public IllegalOptionException(String value) {
        parameter = value;
    }

    public String getParameter() {
        return parameter;
    }
}
