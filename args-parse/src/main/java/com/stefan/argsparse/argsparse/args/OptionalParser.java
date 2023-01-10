package com.stefan.argsparse.argsparse.args;

import com.stefan.argsparse.argsparse.args.exceptions.TooManyArgumentsException;

import java.util.List;

/**
 * @Author StefanYang
 * @Date 2022/9/23 0:52
 * @Version 1.0
 */
interface OptionalParser<T> {
    T parse(List<String> arguments, Option option) throws TooManyArgumentsException;
}
