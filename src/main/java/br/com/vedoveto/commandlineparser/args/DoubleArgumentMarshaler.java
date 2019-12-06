package br.com.vedoveto.commandlineparser.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.INVALID_DOUBLE;
import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.MISSING_DOUBLE;

class DoubleArgumentMarshaler implements ArgumentMarshaler {
    private double doubleValue = 0;

    static double getValue(ArgumentMarshaler am) {
        return ((DoubleArgumentMarshaler) am).doubleValue;
    }

    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            doubleValue = Double.parseDouble(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_DOUBLE);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_DOUBLE, parameter);
        }
    }
}
