package com.codecool;

public class SinglyLinkedList {
    private class Link {

        private final String value;
        private Link next;

        Link(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }

        Link getNext() {
            return next;
        }

        void setNext(Link next) {
            this.next = next;
        }
    }

    private Link head;

    public SinglyLinkedList() {
    }

    // Returns the number at 'index'.
    public String access(int index) {
        int counter = 0;
        Link current = head;
        while (current != null) {
            if (counter == index) {
                return current.getValue();
            }
            current = current.next;
            counter++;
        }
        return "";
    }

    // Returns the index of 'number' if it's in the array, otherwise -1;
    public int search(String string) {
        int index = 0;
        Link current = head;
        while (current != null) {
            if (current.getValue().equals(string)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    // Inserts 'number' at 'index' into the array shifting elements if necessary.
    //
    // e.g. the result of inserting 42 at index 3 into [0, 1, 2, 3, 4] is [0, 1, 2, 42, 3, 4]
    public void insert(int index, String string) {
        if (head == null) {
            head = new Link(string);
            return;
        }
        Link current = head;
        int counter = 0;
        if (counter == index) {
            head = new Link(string);
            head.setNext(current);
            return;
        }
        while (current.next != null) {
            if (counter == index - 1) {
                Link newLink = new Link(string);
                Link afterNewLink = current.next;
                current.setNext(newLink);
                newLink.setNext(afterNewLink);
                return;
            }
            current = current.next;
            counter++;
        }
        current.next = new Link(string);
        current.setNext(current.next);
    }

    // Deletes the element at 'index' from the array.
    //
    //  e.g. the result of deleting index 2 from [0, 1, 2, 3, 4] is [0, 1, 3, 4]
    public void delete(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException();
            } else {
                head = head.getNext();
            }
            return;
        }
        Link elementBeforeIndex = head;
        while (index - 1 > 0) {
            elementBeforeIndex = elementBeforeIndex.getNext();
            index--;
            if (elementBeforeIndex == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        Link elementAtIndex = elementBeforeIndex.getNext();
        if (elementAtIndex == null) {
            throw new IndexOutOfBoundsException();
        }
        elementBeforeIndex.setNext(elementAtIndex.getNext());
    }
}
