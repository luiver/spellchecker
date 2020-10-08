package com.codecool;

/**
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 * Implement your hash table here.  You are required to use the separate
 * chaining strategy that we discussed in lecture, meaning that collisions
 * are resolved by having each cell in the table be a linked list of all of
 * the strings that hashed to that cell.
 */

public class HashTable {
    private final int tableSize;
    private final SinglyLinkedList[] table;
    private final StringHasher stringHasher;

    /**
     * The constructor is given a table size (i.e. how big to make the array)
     * and a StringHasher, which is used to hash the strings.
     *
     * @param tableSize number of elements in the hash array
     *                  hasher    Object that creates the hash code for a string
     * @see StringHasher
     */
    public HashTable(int tableSize, StringHasher hasher) {
        this.tableSize = tableSize;
        this.table = new SinglyLinkedList[tableSize];
        this.stringHasher = hasher;
    }

    private int getPositionByHash(String s) {
        int hashcode = stringHasher.hash(s);
        return Math.abs(hashcode % tableSize);
    }

    /**
     * Takes a string and adds it to the hash table, if it's not already
     * in the hash table.  If it is, this method has no effect.
     *
     * @param s String to add
     */
    public void add(String s) {
        int position = getPositionByHash(s);
        if (table[position] == null) {
            table[position] = new SinglyLinkedList();
        }
        if (!lookup(s)) {
            table[position].insert(0, s);
        }
    }


    /**
     * Takes a string and returns true if that string appears in the
     * hash table, false otherwise.
     *
     * @param s String to look up
     */
    public boolean lookup(String s) {
        int position = getPositionByHash(s);
        SinglyLinkedList sll = table[position];
        if (sll == null){
            return false;
        }
        return sll.search(s) != -1;
    }


    /**
     * Takes a string and removes it from the hash table, if it
     * appears in the hash table.  If it doesn't, this method has no effect.
     *
     * @param s String to remove
     */
    public void remove(String s) {
        if (lookup(s)) {
            int position = getPositionByHash(s);
            SinglyLinkedList sll = table[position];
            sll.delete(sll.search(s));
        }
    }
}
