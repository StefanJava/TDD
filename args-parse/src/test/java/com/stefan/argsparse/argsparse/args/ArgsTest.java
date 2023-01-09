package com.stefan.argsparse.argsparse.args;

import org.junit.jupiter.api.Disabled;
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
    @Test
    public void should_set_boolean_option_to_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");

        assertTrue(option.logging());
    }

    @Test
    public void should_set_boolean_option_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);

        assertFalse(option.logging());
    }

    record BooleanOption(@Option("l") boolean logging) {
    }

    //     - Integer -p 8080

    @Test
    public void should_parse_int_as_option_value() {
        IntOption option = Args.parse(IntOption.class, "-p", "8080");

        assertEquals(8080, option.port());
    }

    record IntOption(@Option("p") int port) {
    }

    //     - String -d /usr/logs

    @Test
    public void should_parse_string_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/logs");

        assertEquals("/usr/logs", option.directory());
    }

    record StringOption(@Option("d") String directory) {
    }

    // happy path
    // Multi options: -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    // sad path:
    // TODO:   - bool -l t / -l t f

    // default value
    // TODO:   - bool : false

    // sad path:
    // TODO:   - int -p / -p 8080 8081
    // TODO:   - string -d / -d /usr/logs /usr/var
    // default value
    // TODO:   - int : 0
    // TODO:   - string : ""

    // -g this is a list -d 1 2 -3 5
    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new int[]{1, 2, -3, 6}, options.decimals());
    }

    record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {
    }

}
