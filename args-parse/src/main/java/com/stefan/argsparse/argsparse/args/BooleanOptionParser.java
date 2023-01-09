package com.stefan.argsparse.argsparse.args;

import java.util.List;

/**
 * @Author StefanYang
 * @Date 2022/9/23 0:52
 * @Version 1.0
 */
class BooleanOptionParser implements OptionalParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) throws TooManyArgumentsException {
        int index = arguments.indexOf("-" + option.value());
        if (index + 1 < arguments.size() && !arguments.get(index + 1).startsWith("-")) throw new TooManyArgumentsException(option.value());
        return index != -1;
    }
}
