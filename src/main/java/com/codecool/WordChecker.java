package com.codecool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 * public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker {
    private WordList wordList;
    private final char[] aToZCharArray = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
    /**
     * Constructor that initializes a new WordChecker with a given WordList.
     *
     * @param wordList Initial word list to check against.
     * @see WordList
     */
    public WordChecker(WordList wordList) {
        this.wordList = wordList;
    }

    /**
     * Returns true if the given word is in the WordList passed to the
     * constructor, false otherwise.
     *
     * @param word Word to chack against the internal word list
     * @return bollean indicating if the word was found or not.
     */
    public boolean wordExists(String word) {
        return wordList.lookup(word);
    }

    /**
     * Returns an ArrayList of Strings containing the suggestions for the
     * given word.  If there are no suggestions for the given word, an empty
     * ArrayList of Strings (not null!) should be returned.
     *
     * @param word String to check against
     * @return A list of plausible matches
     */
    public List<String> getSuggestions(String word) {
        List<String> suggestedStrings = new ArrayList<>();
        if (!wordExists(word)) {
//            suggestedStrings.addAll(afterSwappingSuggestions(word));
//            suggestedStrings.addAll(afterInsertingSuggestions(word));
//            suggestedStrings.addAll(afterDeletingSuggestions(word));
//            suggestedStrings.addAll(afterReplacingSuggestions(word));
//            suggestedStrings.addAll(afterSplittingSuggestions(word));
            suggestedStrings = Stream.of(afterSwappingSuggestions(word), afterInsertingSuggestions(word),
                    afterDeletingSuggestions(word), afterReplacingSuggestions(word), afterSplittingSuggestions(word))
                    .flatMap(x -> x.stream())
                    .collect(Collectors.toList());
        }
        return suggestedStrings;
    }

    private List<String> afterSwappingSuggestions(String word){
        List<String> suggestedStrings = new ArrayList<>();
        for (int i = 0 ; i < word.toCharArray().length-1; i++){
            char[] tempArrayOfChars = word.toCharArray();
            char tempChar = tempArrayOfChars[i];
            tempArrayOfChars[i] = tempArrayOfChars[i+1];
            tempArrayOfChars[i+1] = tempChar;
            String newWord = String.valueOf(tempArrayOfChars);
            if (wordExists(newWord)) {
                suggestedStrings.add(newWord);
            }
        }
        return suggestedStrings;
    }

    private List<String> afterInsertingSuggestions(String word){
        List<String> suggestedStrings = new ArrayList<>();
        return suggestedStrings;
    }

    private List<String> afterDeletingSuggestions(String word){
        List<String> suggestedStrings = new ArrayList<>();
        return suggestedStrings;
    }

    private List<String> afterReplacingSuggestions(String word){
        List<String> suggestedStrings = new ArrayList<>();
        for (int i = 0 ; i < word.toCharArray().length; i++){
            for (char c: aToZCharArray){
                char[] tempArrayOfChars = word.toCharArray();
                tempArrayOfChars[i] = c;
                String newWord = String.valueOf(tempArrayOfChars);
                if (wordExists(newWord)) {
                    suggestedStrings.add(newWord);
                }
            }
        }
        return suggestedStrings;
    }

    private List<String> afterSplittingSuggestions(String word){
        List<String> suggestedStrings = new ArrayList<>();
        return suggestedStrings;
    }

}
