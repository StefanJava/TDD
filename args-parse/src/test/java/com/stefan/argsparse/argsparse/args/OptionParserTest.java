package com.stefan.argsparse.argsparse.args;

import com.stefan.argsparse.argsparse.args.exceptions.IllegalValueException;
import com.stefan.argsparse.argsparse.args.exceptions.InsufficientArgumentsException;
import com.stefan.argsparse.argsparse.args.exceptions.TooManyArgumentsException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.stefan.argsparse.argsparse.args.OptionParserTest.BoolOptionParserTest.option;
import static org.junit.jupiter.api.Assertions.*;

public class OptionParserTest {

    @Nested
    class UnaryOptionParserTest {
        @Test
        public void should_not_accept_extra_argument_for_single_valued_option() {
            TooManyArgumentsException exception = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(Arrays.asList("-p", "8080", "8081"), option("p")));
            assertEquals("p", exception.getOption());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-p -l", "-p"})
        public void should_not_accept_insufficient_argument_for_single_valued_option(String parameters) {
            InsufficientArgumentsException exception = assertThrows(InsufficientArgumentsException.class,
                    () -> OptionParsers.unary(0, Integer::parseInt).parse(List.of(parameters.split(" ")), option("p")));
            assertEquals("p", exception.getOption());
        }

        @Test
        public void should_set_default_value_to_0_for_int_option() {
            assertEquals(0, OptionParsers.unary(0, Integer::parseInt).parse(List.of(), option("p")));
        }

        @Test
        public void should_not_accept_extra_argument_for_string_single_valued_option() {
            TooManyArgumentsException exception = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.unary("", String::valueOf).parse(Arrays.asList("-d", "/usr/logs", "/usr/vars"), option("d")));
            assertEquals("d", exception.getOption());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-d -l", "-d"})
        public void should_not_accept_insufficient_argument_for_string_single_valued_option(String parameters) {
            InsufficientArgumentsException exception = assertThrows(InsufficientArgumentsException.class,
                    () -> OptionParsers.unary("", String::valueOf).parse(List.of(parameters.split(" ")), option("d")));
            assertEquals("d", exception.getOption());
        }

        @Test
        public void should_set_default_value_to_0_for_string_int_option() {
            Function<String, Object> whatever = (it) -> null;
            Object defaultValue = new Object();
            assertEquals(defaultValue, OptionParsers.unary(defaultValue, whatever).parse(List.of(), option("d")));
        }

        @Test
        public void should_parse_value_if_flag_present() {
            Object parsed = new Object();
            Function<String, Object> parse = it -> parsed;
            Object whatever = new Object();
            assertEquals(parsed, OptionParsers.unary(whatever, parse).parse(Arrays.asList("-p", "8080"), option("p")));
        }
    }

    @Nested
    class BoolOptionParserTest {

        @Test
        public void should_set_boolean_option_to_true_if_flag_present() {
            ArgsTest.BooleanOption option = Args.parse(ArgsTest.BooleanOption.class, "-l");

            assertTrue(option.logging());
        }

        @Test
        public void should_not_accept_extra_argument_for_boolean_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.bool().parse(Arrays.asList("-l", "t"), option("l")));
            assertEquals("l", e.getOption());
        }

        @Test
        public void should_not_accept_extra_arguments_for_boolean_option() {
            TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                    () -> OptionParsers.bool().parse(Arrays.asList("-l", "t", "d"), option("l")));
            assertEquals("l", e.getOption());
        }

        @Test
        public void should_set_default_value_to_false_if_option_not_present() throws TooManyArgumentsException {
            assertFalse(OptionParsers.bool().parse(List.of(), option("l")));
        }

        static Option option(String value) {
            return new Option() {

                @Override
                public Class<? extends Annotation> annotationType() {
                    return Option.class;
                }

                @Override
                public String value() {
                    return value;
                }
            };
        }
    }

    @Nested
    class ListOptionParserTest {
        // -g "this" "is" {"this", "is"}
        @Test
        public void should_parse_list_value() {
            assertArrayEquals(new String[]{"this", "is"}, OptionParsers.list(String[]::new, String::valueOf)
                    .parse(Arrays.asList("-g", "this", "is"), option("g")));
        }

        // default value []
        @Test
        public void should_parse_list_default_value_to_empty_array() {
            assertArrayEquals(new String[]{}, OptionParsers.list(String[]::new, String::valueOf)
                    .parse(List.of(), option("g")));
        }
        // -d a throw exception
        @Test
        public void should_throw_exception_if_value_can_not_parser() {
            Function<String, String> function = it -> {
                throw new RuntimeException();
            };
            IllegalValueException exception = assertThrows(IllegalValueException.class, () -> OptionParsers.list(String[]::new, function)
                    .parse(Arrays.asList("-g", "this", "is"), option("g")));
            assertEquals("g", exception.getOption());
            assertEquals("this", exception.getValue());
        }

        @Test
        public void should_not_treat_negative_int_as_flag() {
            assertArrayEquals(new Integer[]{-1, -2}, OptionParsers.list(Integer[]::new, Integer::parseInt).parse(Arrays.asList("-g", "-1", "-2"), option("g")));
        }
    }
}
