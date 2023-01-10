package com.stefan.argsparse.argsparse.args;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

            Object[] values = Arrays.stream(declaredConstructor.getParameters()).map(it -> {
                try {
                    return parseOptions(arguments, it);
                } catch (TooManyArgumentsException e) {
                    throw new RuntimeException(e);
                }
            }).toArray();
            return (T) declaredConstructor.newInstance(values);
        } catch (IllegalOptionException exception) {
            throw exception;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOptions(List<String> arguments, Parameter parameter) throws TooManyArgumentsException {
        if (!parameter.isAnnotationPresent(Option.class)) throw new IllegalOptionException(parameter.getName());
        return PARSER.get(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    public static Map<Class<?>, OptionalParser> PARSER = Map.of(
            boolean.class, new BooleanOptionParser(),
            int.class, new SingleValueOptionParser<>(0, Integer::parseInt),
            String.class, new SingleValueOptionParser<>("", String::valueOf));

}
