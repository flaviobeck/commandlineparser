package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;

class BooleanArgumentMarshaler implements ArgumentMarshaler<Boolean> {
    private boolean booleanValue = false;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        booleanValue = true;
    }

    @Override
    public Boolean get() {
        return booleanValue;
    }
}
