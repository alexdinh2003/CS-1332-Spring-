package src;

import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author HOANG GIA NGHI(ALEX) DINH, [FIRST, LAST]
 * @version 1.0
 * @userid ndinh31 (i.e. gburdell3)
 * @GTID 903733512
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * https://www.javatpoint.com/java-program-to-insert-a-new-node
 * -at-the-beginning-of-the-circular-linked-list
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data into the data structure!");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Your input is out of bounds. "
                    + "The bounds are between 0 and " + size);
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(data);
        if (index == 0) {
            this.addToFront(data);
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            int i = 0;
            while (i < index - 1) {
                curr = curr.getNext();
                i++;

            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data into the data structure!");
        }
        if (head == null) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<>(head.getData());
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            newNode.setData(head .getData());
            head.setData(data);
        }

        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data into the data structure!");
        }
        this.addToFront(data);
        head = head.getNext();
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bound of the list!");
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return this.removeFromFront();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            T removedData = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return removedData;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            return null;
        }
        if (size == 0) {
            throw new NoSuchElementException("This list is empty, so no data available to remove!");
        }
        if (size == 1) {
            T temp = head.getData();
            head = null;
            size--;
            return temp;
        } else {
            T removeValue = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return removeValue;
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            return null;
        }
        if (size == 0) {
            throw new NoSuchElementException("This list is empty, so no data available to remove!");
        }
        CircularSinglyLinkedListNode<T> curr = head;
        if (size == 1) {
            T temp = head.getData();
            size--;
            head = null;
            return temp;
        } else {
            return this.removeAtIndex(size - 1);
        }

    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bound of the list!");
        }
        if (index == 0) {
            T item = head.getData();
            return item;
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }

    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data  == null) {
            throw new NoSuchElementException("The data is not found/or cannot be null!");
        }
        if (head == null) {
            return null;
        }
        int lastIndex = -1;
        CircularSinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(data)) {
                lastIndex = i;
            }
            curr = curr.getNext();
        }
        if (lastIndex == -1) {
            throw new NoSuchElementException("The data given was not found! Try again!");
        }
        T removedData = this.get(lastIndex);
        this.removeAtIndex(lastIndex);
        return removedData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> curr = head;
        int count = 0;
        for (int i = 0; i < size; i++) {
            arr[count] = curr.getData();
            curr = curr.getNext();
            count++;
        }

        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
