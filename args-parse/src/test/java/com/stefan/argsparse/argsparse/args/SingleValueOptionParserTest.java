package com.stefan.argsparse.argsparse.args;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.stefan.argsparse.argsparse.args.BooleanOptionParserTest.option;
import static org.junit.jupiter.api.Assertions.*;

public class SingleValueOptionParserTest {

    @Test
    public void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException exception = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(Arrays.asList("-p", "8080", "8081"), option("p")));
        assertEquals("p", exception.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_valued_option(String parameters) {
        InsufficientArgumentsException exception = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValueOptionParser<>(0, Integer::parseInt).parse(List.of(parameters.split(" ")), option("p")));
        assertEquals("p", exception.getOption());
    }

    @Test
    public void should_set_default_value_to_0_for_int_option() {
        assertEquals(0, new SingleValueOptionParser<>(0, Integer::parseInt).parse(Arrays.asList(), option("p")));
    }

    @Test
    public void should_not_accept_extra_argument_for_string_single_valued_option() {
        TooManyArgumentsException exception = assertThrows(TooManyArgumentsException.class,
                () -> new SingleValueOptionParser<>("", String::valueOf).parse(Arrays.asList("-d", "/usr/logs", "/usr/vars"), option("d")));
        assertEquals("d", exception.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-d -l", "-d"})
    public void should_not_accept_insufficient_argument_for_string_single_valued_option(String parameters) {
        InsufficientArgumentsException exception = assertThrows(InsufficientArgumentsException.class,
                () -> new SingleValueOptionParser<>("", String::valueOf).parse(List.of(parameters.split(" ")), option("d")));
        assertEquals("d", exception.getOption());
    }

    @Test
    public void should_set_default_value_to_0_for_string_int_option() {
        Function<String, Object> whatever = (it) -> null;
        Object defaultValue = new Object();
        assertEquals(defaultValue, new SingleValueOptionParser<>(defaultValue, whatever).parse(Arrays.asList(), option("d")));
    }

    @Test
    public void should_parse_value_if_flag_present() {
        Object parsed = new Object();
        Function<String, Object> parse = it -> parsed;
        Object whatever = new Object();
        assertEquals(parsed, new SingleValueOptionParser<>(whatever, parse).parse(Arrays.asList("-p", "8080"), option("p")));
    }

}
