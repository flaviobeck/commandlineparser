package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;

interface ArgumentMarshaler {
    void set(Iterator<String> currentArgument) throws ArgsException;
}
