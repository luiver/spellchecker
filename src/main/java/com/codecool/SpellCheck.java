package com.codecool;

import java.io.IOException;
import java.io.PrintStream;

public class SpellCheck
{
    public static void main(final String[] array) {
        if (array.length == 0) {
            showUsageMessage();
            return;
        }
        final String s = array[array.length - 1];
        String s2 = "wordlist.txt";
        StringHasher o = new LousyStringHasher();
        PrintStream out = System.out;
        boolean b = false;
        for (int i = 0; i < array.length - 1; ++i) {
            switch (array[i]) {
                case "-degenerate":
                    o = new DegenerateStringHasher();
                    break;
                case "-lousy":
                    o = new LousyStringHasher();
                    break;
                case "-better":
                    o = new BetterStringHasher();
                    break;
                case "-quiet":
                    out = new PrintStream(new NullOutputStream());
                    b = true;
                    break;
                case "-wordlist":
                    if (++i >= array.length - 1) {
                        showUsageMessage();
                        return;
                    }
                    s2 = array[i];
                    break;
            }
        }
        if (array[array.length - 1].charAt(0) == '-') {
            showUsageMessage();
            return;
        }
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            new Checker().check(s, s2, o, out);
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (b) {
                System.out.println("Checker ran in " + (currentTimeMillis2 - currentTimeMillis) + "ms");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void showUsageMessage() {
        System.out.println("Usage: java SpellCheck [options] inputFilename");
        System.out.println();
        System.out.println("    options");
        System.out.println("    -------");
        System.out.println("    -degenerate");
        System.out.println("        runs the spell checker with the degenerate word hashing algorithm");
        System.out.println();
        System.out.println("    -lousy");
        System.out.println("        runs the spell checker with a lousy word hashing algorithm (default)");
        System.out.println();
        System.out.println("    -better");
        System.out.println("        runs the spell checker with a better word hashing algorithm");
        System.out.println();
        System.out.println("    -quiet");
        System.out.println("        runs the spell checker without any output, reporting the total time");
        System.out.println("        taken to load the dictionary and perform the spell check");
        System.out.println();
        System.out.println("    -wordlist wordlistFilename");
        System.out.println("        runs the spell checker using the wordlist specified, rather than");
        System.out.println("        the default (wordlist.txt)");
        System.out.println();
        System.out.println("    example");
        System.out.println("    -------");
        System.out.println("    java SpellCheck -wordlist biglist.txt -better -quiet big-input.txt");
        System.out.println("        executes the spell checker using the wordlist 'biglist.txt', the");
        System.out.println("        better word hashing algorithm, in quiet mode (i.e. no output),");
        System.out.println("        on the input file 'big-input.txt'");
    }
}
