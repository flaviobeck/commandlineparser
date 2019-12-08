package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;

class BooleanArgumentMarshaler implements ArgumentMarshaler<Boolean> {
    private boolean booleanValue = false;

    public Class<Boolean> getType() {
        return Boolean.class;
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        booleanValue = true;
    }

    public Boolean get() {
        return booleanValue;
    }
}
