package br.com.vedoveto.commandlineparser.args;

import java.util.HashMap;
import java.util.Map;

import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

class ArgsSchemaParser {
    private final Map<Character, ArgumentMarshaler> marshalers;

    ArgsSchemaParser() {
        marshalers = new HashMap<Character, ArgumentMarshaler>();
    }

    public Map<Character, ArgumentMarshaler> parseSchema(String schema) throws ArgsException {
        marshalers.clear();

        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());

        return marshalers;
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);

        if (elementTail.length() == 0)
            marshalers.put(elementId, new BooleanArgumentMarshaler());
        else if (elementTail.equals("*"))
            marshalers.put(elementId, new StringArgumentMarshaler());
        else if (elementTail.equals("#"))
            marshalers.put(elementId, new IntegerArgumentMarshaler());
        else if (elementTail.equals("##"))
            marshalers.put(elementId, new DoubleArgumentMarshaler());
        else
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }
}
