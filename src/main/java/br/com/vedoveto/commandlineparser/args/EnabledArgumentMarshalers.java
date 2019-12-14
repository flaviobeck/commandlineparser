package br.com.vedoveto.commandlineparser.args;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class EnabledArgumentMarshalers {
    private Map<String, Class> argumentMarshalersMap = new HashMap<>();

    public EnabledArgumentMarshalers() {
        registerArgumentMarshaler("", BooleanArgumentMarshaler.class);
        registerArgumentMarshaler("*", StringArgumentMarshaler.class);
        registerArgumentMarshaler("#", IntegerArgumentMarshaler.class);
        registerArgumentMarshaler("##", DoubleArgumentMarshaler.class);
    }

    public Map<String, Class> getEnabledArgumentMarshalers() {
        return Collections.unmodifiableMap(argumentMarshalersMap);
    }

    private <T extends ArgumentMarshaler> void registerArgumentMarshaler(String schemaElement, Class<T> argumentMarshaler) {
        argumentMarshalersMap.put(schemaElement, argumentMarshaler);
    }
}
