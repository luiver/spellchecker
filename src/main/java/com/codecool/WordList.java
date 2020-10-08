package com.codecool;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordList {
    private final HashTable hashTable;

    public WordList(final String fileName, final StringHasher stringHasher) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        final int wordsInFile = Integer.parseInt(bufferedReader.readLine());
        this.hashTable = new HashTable((int)(wordsInFile * 1.2), stringHasher);
        for (int i = 0; i < wordsInFile; ++i) {
            this.hashTable.add(bufferedReader.readLine().trim().toUpperCase());
        }
        bufferedReader.close();
    }

    public boolean lookup(final String s) {
        return this.hashTable.lookup(s.toUpperCase());
    }
}