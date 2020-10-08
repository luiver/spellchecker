package com.codecool;

import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

public class Checker {
    public void check(final String wordsToSearchFileName, final String wordListFileName, final StringHasher stringHasher, final PrintStream printStream) throws IOException {
        final WordList list = new WordList(wordListFileName, stringHasher);
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(Helper.getPathForResourceFile(wordsToSearchFileName)));
        String currentWord = bufferedReader.readLine();
        WordLineReader wordLineReader = new WordLineReader(currentWord);
        final WordChecker wordChecker = new WordChecker(list);
        while (currentWord != null) {
            while (wordLineReader.hasNextWord()) {
                final String upperCase = wordLineReader.nextWord().toUpperCase();
                if (!wordChecker.wordExists(upperCase)) {
                    final List<String> suggestions = wordChecker.getSuggestions(upperCase);
                    printStream.println();
                    printStream.println(currentWord);
                    printStream.println("     word not found: " + upperCase);
                    if (suggestions.size() <= 0) {
                        continue;
                    }
                    Collections.sort(suggestions);
                    printStream.println("  perhaps you meant: ");
                    for (String suggestion : suggestions) {
                        printStream.println("          " + suggestion + " ");
                    }
                }
            }
            currentWord = bufferedReader.readLine();
            wordLineReader = new WordLineReader(currentWord);
        }
        bufferedReader.close();
    }
}
