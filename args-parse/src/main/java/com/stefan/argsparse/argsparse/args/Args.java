package com.stefan.argsparse.argsparse.args;

import com.stefan.argsparse.argsparse.args.exceptions.IllegalOptionException;
import com.stefan.argsparse.argsparse.args.exceptions.TooManyArgumentsException;
import com.stefan.argsparse.argsparse.args.exceptions.UnsupportedOptionTypeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author StefanYang
 * @Date 2022/9/1 0:22
 * @Version 1.0
 */
public class Args {

    public static <T> T parse(Class<T> optionsClass, String... args) {

        try {
            List<String> arguments = Arrays.asList(args);
            Constructor<?> declaredConstructor = optionsClass.getDeclaredConstructors()[0];

            Object[] values = Arrays.stream(declaredConstructor.getParameters()).map(it -> parseOptions(arguments, it)).toArray();

            return optionsClass.cast(declaredConstructor.newInstance(values));
        } catch (UnsupportedOptionTypeException | IllegalOptionException exception) {
            throw exception;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOptions(List<String> arguments, Parameter parameter) throws TooManyArgumentsException {
        if (!parameter.isAnnotationPresent(Option.class)) throw new IllegalOptionException(parameter.getName());
        Option option = parameter.getAnnotation(Option.class);
        if (!PARSER.containsKey(parameter.getType())) {
            throw new UnsupportedOptionTypeException(option.value(), parameter.getType());
        }
        return PARSER.get(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    public static Map<Class<?>, OptionalParser<?>> PARSER = Map.of(
            boolean.class, OptionParsers.bool(),
            int.class, OptionParsers.unary(0, Integer::parseInt),
            String.class, OptionParsers.unary("", String::valueOf),
            String[].class, OptionParsers.list(String[]::new, String::valueOf),
            Integer[].class, OptionParsers.list(Integer[]::new, Integer::parseInt));
}
