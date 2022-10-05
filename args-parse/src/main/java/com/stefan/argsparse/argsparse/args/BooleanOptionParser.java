package com.stefan.argsparse.argsparse.args;

import java.util.List;

/**
 * @Author StefanYang
 * @Date 2022/9/23 0:52
 * @Version 1.0
 */
class BooleanOptionParser implements OptionalParser {

    @Override
    public Object parse(List<String> arguments, Option option) {
        return arguments.contains("-" + option.value());
    }
}
