import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author HOANG GIA NGHI(ALEX), DINH  [First, Last]
 * @version 1.0
 * @userid ndinh31   (i.e. gburdell3)
 * @GTID 903733512
 * <p>
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * Ronith (Also CS 1332 classmate. He helped me answer the
 * questions on how to approach the problems on HW1)
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * https://www.w3schools.com/java/java_arraylist.asp
 * https://github.com/ericyuegu/CS-1332-Data-Structures-and-Algorithms
 */

public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * <p>
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */

    //create constructor
    public ArrayList() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * This is resize array.
     * When the array is full, it will invoke this method to create double size.
     */
    private void resizeTable() {
        if (size == backingArray.length) {
            T[] newBackingArray = (T[]) (new Object[backingArray.length * 2]);
            for (int i = 0; i < backingArray.length; i++) {
                newBackingArray[i] = backingArray[i];
            }
            backingArray = newBackingArray;
        }
    }

    /**
     * Adds the element to the specified index.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Your input is out of bounds. "
                    + "The bounds are between 0 and" + size);
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data!");
        } else if (size == backingArray.length) {
            resizeTable();
        } else if (index == size) {
            backingArray[index] = data;
            size += 1;
        } else if (index == 0) {
            addToFront(data);
        } else {

            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
            size++;
        }


    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data!");
        }
        if (size == backingArray.length) {
            resizeTable();
        }
        if (size == 0) {
            backingArray[0] = data;
            size += 1;
        } else {
            int i = size;
            while (i != 0) {
                backingArray[i] = backingArray[i - 1];
                i -= 1;
            }
            backingArray[0] = data;
            size++;
        }
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add the null data!");
        }
        if (size == backingArray.length) {
            resizeTable();
        }
        backingArray[size] = data;
        size += 1;

    }

    /**
     * Removes and returns the element at the specified index.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index does not exist! The bounds are "
                    + "between 0 and " + size);
        }
        T removed = backingArray[index];
        if (index == size - 1) {
            backingArray[index] = null;
        } else {
            backingArray[index] = null;
            for (int i = index; i < size; i++) {
                backingArray[i] = backingArray[i + 1];
            }
        }
        size -= 1;
        return removed;
    }

    /**
     * Removes and returns the first element of the list.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The list has empty elements now! Please "
                    + "check back!");
        }
        T storageRemoved = backingArray[0];
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return storageRemoved;
    }

    /**
     * Removes and returns the last element of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("The list has no elements. You cannot remove "
                    + "the elements from the back.");
        }

        T removeStorageValue = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return removeStorageValue;
    }

    /**
     * Returns the element at the specified index.
     * <p>
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("The index is less than 0! The bounds are "
                    +  "between 0 and " + size);
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("The index is greater than or equal "
                    +  "to size! the bounds are between 0 and " + size);
        }
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
