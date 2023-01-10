package com.stefan.argsparse.argsparse.args;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class BooleanOptionParserTest {

    @Test
    public void should_set_boolean_option_to_true_if_flag_present() {
        ArgsTest.BooleanOption option = Args.parse(ArgsTest.BooleanOption.class, "-l");

        assertTrue(option.logging());
    }

    @Test
    public void should_not_accept_extra_argument_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new BooleanOptionParser().parse(Arrays.asList("-l", "t"), option("l")));
        assertEquals("l", e.getOption());
    }

    @Test
    public void should_not_accept_extra_arguments_for_boolean_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> new BooleanOptionParser().parse(Arrays.asList("-l", "t", "d"), option("l")));
        assertEquals("l", e.getOption());
    }

    @Test
    public void should_set_default_value_to_false_if_option_not_present() throws TooManyArgumentsException {
        assertFalse(new BooleanOptionParser().parse(Arrays.asList(), option("l")));
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
