package com.codecool;

public class WordLineReader
{
    private final String line;
    private int currentChar;
    private String nextWord;

    public WordLineReader(final String line) {
        this.line = line;
        if (line == null) {
            this.nextWord = null;
        }
        else {
            this.currentChar = 0;
            this.readNextWord();
        }
    }

    public boolean hasNextWord() {
        return this.nextWord != null;
    }

    public String nextWord() {
        final String nextWord = this.nextWord;
        if (this.nextWord != null) {
            this.readNextWord();
        }
        return nextWord;
    }

    private void readNextWord() {
        this.nextWord = "";
        while (this.currentChar < this.line.length() && !this.isWordStartingOrEndingLetter(this.line.charAt(this.currentChar))) {
            ++this.currentChar;
        }
        while (this.currentChar < this.line.length() && this.isWordLetter(this.line.charAt(this.currentChar))) {
            this.nextWord += this.line.charAt(this.currentChar);
            ++this.currentChar;
        }
        if (this.nextWord.length() == 0) {
            this.nextWord = null;
            return;
        }
        if (!this.isWordStartingOrEndingLetter(this.nextWord.charAt(this.nextWord.length() - 1))) {
            this.nextWord = this.nextWord.substring(0, this.nextWord.length() - 1);
        }
    }

    private boolean isWordStartingOrEndingLetter(final char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    private boolean isWordLetter(final char c) {
        return this.isWordStartingOrEndingLetter(c) || c == '-' || c == '\'';
    }
}
