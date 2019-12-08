package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;

interface ArgumentMarshaler<T> {
    void set(Iterator<String> currentArgument) throws ArgsException;

    T get();
}
