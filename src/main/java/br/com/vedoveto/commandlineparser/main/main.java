package br.com.vedoveto.commandlineparser.main;

import br.com.vedoveto.commandlineparser.args.Args;

public class main {
    public static void main(String... args) {
        Args parse = new Args("b,s*,i#,d##", new String[]{"-s", "string"});

        boolean b = parse.get('b', Boolean.class);
        System.out.println(b);
    }
}
