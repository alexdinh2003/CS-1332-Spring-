import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author HOANG GIA NGHI, DINH[First, Last]
 * @version 1.0
 * @userid ndinh31 (i.e. gburdell3)
 * @GTID 903733512 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     * 
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null!");
        }
        backingArray = (T[]) new Comparable[data.size() * 2 + 1];
        size = data.size();

        for (int i = 0; i < size; i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data in the inputted Arraylist cannot be null.");
            }
            backingArray[i + 1] = data.get(i);
        }

        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Private recursive method to make sure that the elements of MaxHeap are in the correct order
     *
     * @param index index of data that downHeap is applied
     */

    private void downHeap(int index) {
        //takes in a starting index, and swaps nodes if the parent is smaller than the child,
        //or if the left child is greater than the right node.

        while (size >= index * 2) {
            int childIndex = index * 2;
            if (childIndex < size && !(backingArray[childIndex].compareTo(backingArray[childIndex + 1]) > 0)) {
                childIndex++;
            }

            if (backingArray[index].compareTo(backingArray[childIndex]) > 0) {
                break;
            }
            swap(index, childIndex);
            index = childIndex;
        }

    }

    /**
     * Helper method to swap the data at the two indexes provided
     *
     * @param curr current data the methods needs to swap
     * @param swapIndex index of the next data that needs to be swapped
     */

    private void swap(int curr, int swapIndex) {
        T tmp = backingArray[curr];
        backingArray[curr] = backingArray[swapIndex];
        backingArray[swapIndex] = tmp;
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        // Time complexity: O(log n)
        // Add at the bottom up-heap or heapify
        // Add to the next spot in the array to maintain completeness
        // Up-Heap starting from the new data to fix order property:
        // a) Compare the data with the parent
        // b) Swap the data with the parent if necessary
        // c) Continue until the top of the heap is reached or until no swap is needed
        if (data == null) {
            throw new IllegalArgumentException("The data cannot be null");
        }

        if (size >= backingArray.length) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        size++;
        backingArray[size] = data;

        //curr/2 = parent
        int curr = size;
        while (curr != 1 && backingArray[curr].compareTo(backingArray[curr / 2]) > 0) {
            swap(curr, curr / 2);
            curr = curr / 2;
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        // Remove from the root Down-heap
        // Move the last element of the heap to replace the root
        // -> Delete the last element
        // Down-Heap starting from the root to fix the order property:
        // a) If two children, compare data with higher priority child
        // b) If one child (must be left), compare data with the child
        // c) Swap if necessary based on comparison
        // d) Continue down the heap until no swap is made or a leaf is reached
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty");
        }
        T removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return removedData;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty!");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Returns the backing array of the heap.
     *
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
     * Returns the size of the heap.
     *
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
