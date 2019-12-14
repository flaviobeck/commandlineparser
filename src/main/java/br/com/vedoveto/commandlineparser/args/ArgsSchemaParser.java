package br.com.vedoveto.commandlineparser.args;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.INVALID_ARGUMENT_NAME;

class ArgsSchemaParser {
    private final Map<Character, ArgumentMarshaler> marshalers;
    private final EnabledArgumentMarshalers enabledArgumentMarshalers;

    ArgsSchemaParser() {
        marshalers = new HashMap<>();
        enabledArgumentMarshalers = new EnabledArgumentMarshalers();
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
        validateSchemaElementId(elementId);

        ArgumentMarshaler argumentMarshaler = findArgumentMarshalerBySchemaElement(element);

        marshalers.put(elementId, argumentMarshaler);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId))
            throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
    }

    private ArgumentMarshaler findArgumentMarshalerBySchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);

        try {
            Optional<Map.Entry<String, Class>> first = enabledArgumentMarshalers.getEnabledArgumentMarshalers().entrySet()
                    .stream().filter((entry) -> entry.getKey().equals(elementTail))
                    .findFirst();

            if (first.isPresent()) {
                ArgumentMarshaler argumentMarshaler = (ArgumentMarshaler) first.get().getValue().newInstance();
                return argumentMarshaler;
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }

        throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }
}
