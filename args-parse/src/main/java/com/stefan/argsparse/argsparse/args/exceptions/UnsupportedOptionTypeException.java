package com.stefan.argsparse.argsparse.args.exceptions;

/**
 * @Description:
 * @Date: 2023/1/10 17:14
 * @Author: stefanyang
 */
public class UnsupportedOptionTypeException extends RuntimeException {

    private final String value;

    private final Class<?> type;

    public UnsupportedOptionTypeException(String value, Class<?> type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }
}
