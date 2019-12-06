package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;

class BooleanArgumentMarshaler implements ArgumentMarshaler {
    private boolean booleanValue = false;

    static boolean getValue(ArgumentMarshaler am) {
        return ((BooleanArgumentMarshaler) am).booleanValue;
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        booleanValue = true;
    }
}
