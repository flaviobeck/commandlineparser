package br.com.vedoveto.commandlineparser.args;

import java.util.*;

import static br.com.vedoveto.commandlineparser.args.ArgsException.ErrorCode.UNEXPECTED_ARGUMENT;

public class Args {
    private final Set<Character> argsFound;
    private final ArgsSchemaParser schemaParser;
    private final Map<Character, ArgumentMarshaler> marshalers;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] args) throws ArgsException {
        argsFound = new HashSet<Character>();
        schemaParser = new ArgsSchemaParser();

        marshalers = schemaParser.parseSchema(schema);
        parseArgumentStrings(Arrays.asList(args));
    }

    public <T> T get(char arg, Class<T> argClass) {
        return (T) marshalers.get(arg).get();
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
            String argString = currentArgument.next();

            if (argString.startsWith("-"))
                parseArgumentCharacters(argString.substring(1));
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++)
            parseArgumentCharacter(argChars.charAt(i));
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);

        if (m != null) {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            } catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        } else
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
    }

    public int cardinality() {
        return argsFound.size();
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }
}