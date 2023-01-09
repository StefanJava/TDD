package com.stefan.argsparse.argsparse.args;

import java.util.List;
import java.util.function.Function;

/**
 * @Author StefanYang
 * @Date 2022/9/23 0:52
 * @Version 1.0
 */
class SingleValueOptionParser<T> implements OptionalParser<T> {

    Function<String, T> valueParser;

    public SingleValueOptionParser(Function<String, T> valueParser) {
        this.valueParser = valueParser;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value()) + 1;
        String value = arguments.get(index);
        return valueParser.apply(value);
    }

}
