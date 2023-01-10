package com.stefan.argsparse.argsparse.args;

import com.stefan.argsparse.argsparse.args.exceptions.IllegalOptionException;
import com.stefan.argsparse.argsparse.args.exceptions.UnsupportedOptionTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author StefanYang
 * @Date 2022/8/28 18:03
 * @Version 1.0
 */
public class ArgsTest {
    // -l -p 8080 -d /usr/logs
    // [-l], [-p, 8080], [-d, /usr/logs]
    // {-l:[], -p:[8080], -d:[/usr/logs]}
    // Single Option:
    //     - Bool -l

    record BooleanOption(@Option("l") boolean logging) {
    }

    //     - Integer -p 8080

    //     - String -d /usr/logs

    // happy path
    // Multi options: -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    @Test
    public void should_throw_illegal_option_exception_if_annotation_not_present() {
        IllegalOptionException exception = assertThrows(IllegalOptionException.class, () -> Args.parse(MultiOptionsWithoutAnnotation.class, "-l", "-p", "8080", "-d", "/usr/logs"));
        assertEquals("port", exception.getParameter());
    }

    @Test
    public void should_raise_exception_if_type_not_supported() {
        UnsupportedOptionTypeException e = assertThrows(UnsupportedOptionTypeException.class,
                () -> Args.parse(OptionWithUnsupportedTye.class, "-l", "abc"));
        assertEquals("l", e.getValue());
        assertEquals(Object.class, e.getType());
    }

    static record OptionWithUnsupportedTye(@Option("l") Object logging) {

    }

    record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    record MultiOptionsWithoutAnnotation(@Option("l") boolean logging, int port, @Option("d") String directory) {

    }

    // sad path:
    // - bool -l t / -l t f

    // default value
    // - bool : false

    // sad path:
    // - int -p / -p 8080 8081
    // - string -d / -d /usr/logs /usr/var
    // default value
    // - int : 0
    // - string : ""

    // -g this is a list -d 1 2 -3 5
    @Test
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new Integer[]{1, 2, -3, 5}, options.decimals());
    }

    record ListOptions(@Option("g") String[] group, @Option("d") Integer[] decimals) {
    }

}
