package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.MISSING_STRING;

class StringArgumentMarshaler implements ArgumentMarshaler {
    private String stringValue = "";

    static String getValue(ArgumentMarshaler am) {
        return ((StringArgumentMarshaler) am).stringValue;
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }
}