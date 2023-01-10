package com.stefan.argsparse.argsparse.args.exceptions;

/**
 * @Description:
 * @Date: 2023/1/10 16:58
 * @Author: stefanyang
 */
public class IllegalValueException extends RuntimeException {

    private final String option;

    private final String value;


    public IllegalValueException(String option, String value) {
        this.option = option;
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public String getValue() {
        return value;
    }
}
