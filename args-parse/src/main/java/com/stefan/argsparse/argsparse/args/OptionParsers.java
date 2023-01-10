package com.stefan.argsparse.argsparse.args;

import com.stefan.argsparse.argsparse.args.exceptions.IllegalValueException;
import com.stefan.argsparse.argsparse.args.exceptions.InsufficientArgumentsException;
import com.stefan.argsparse.argsparse.args.exceptions.TooManyArgumentsException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * @Author StefanYang
 * @Date 2022/9/23 0:52
 * @Version 1.0
 */
class OptionParsers {

    public static OptionalParser<Boolean> bool() {
        return (arguments, option) -> values(arguments, option, 0).isPresent();
    }

    public static <T> OptionalParser<T> unary(T defaultValue, Function<String, T> valueParser) {
        return (arguments, option) -> values(arguments, option, 1).map(it -> parseValue(option, it.get(0), valueParser)).orElse(defaultValue);
    }

    public static <T> OptionalParser<T[]> list(IntFunction<T[]> generator, Function<String, T> valueParser) {
        return (arguments, option) -> values(arguments, option).map(
                it -> it.stream().map(value -> parseValue(option, value, valueParser)).toArray(generator)).orElse(generator.apply(0));
    }

    private static Optional<List<String>> values(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return Optional.ofNullable(index == -1 ? null : getValues(arguments, index));
    }

    private static Optional<List<String>> values(List<String> arguments, Option option, int expectedValue) {
        return values(arguments, option).map(it -> checkSize(option, expectedValue, it));
    }

    private static List<String> checkSize(Option option, int expectedValue, List<String> values) {
        if (values.size() < expectedValue) throw new InsufficientArgumentsException(option.value());
        if (values.size() > expectedValue) throw new TooManyArgumentsException(option.value());
        return values;
    }

    private static <T> T parseValue(Option option, String value, Function<String, T> valueParser) {
        try {
            return valueParser.apply(value);
        } catch (Exception e) {
            throw new IllegalValueException(option.value(), value);
        }

    }

    private static List<String> getValues(List<String> arguments, int index) {
        return arguments.subList(index + 1, IntStream.range(index + 1, arguments.size())
                .filter(it -> arguments.get(it).matches("^-[a-zA-Z-]+$")).findFirst().orElse(arguments.size()));
    }

}
