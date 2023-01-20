import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for ArrayList
 * that I created and used resources from CS 1332 classmates and in Piazza.
 * *
 *
 * @author HOANG GIA NGHI DINH
 * @version 1.0
 */
public class ArrayListNghiTest {
    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    //Test exception if addAtIndex is less than zero
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionAddAtIndexSmaller() {
        list.addAtIndex(-3, "a");
    }

    //list is empty so only acceptable and index is 0
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexOutOfBoundsAboveTheSize() {
        list.addAtIndex(5, "a");
    }

    //test addAtIndex with the null data
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexNullData() {
        list.addAtIndex(0, null);
    }

    //test Java's implementation of ArrayList
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionOrderOutOfBoundsAndNullData() {
        list.addAtIndex(-7, null);
    }

    //test if add to Front is Null Data
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToFrontNullData() {
        list.addToFront(null);
    }

    //Test if add to Front whether it is Front Past Full Capacity
    @Test(timeout = TIMEOUT)
    public void addToFrontPastFullCapacity() {
        assertEquals(0, list.size());

        for (int i = 0; i < ArrayList.INITIAL_CAPACITY + 1; i++) {
            String temp = i + "a";
            list.addToFront(temp);
        }

        assertEquals(10, list.size());
        //must cast to an object array here or else it will throw and error
        assertEquals(18, ((Object[]) list.getBackingArray()).length);
    }

    //Test if add to the back a null data
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToBackNullData() {
        list.addToBack(null);
    }

    //Test if add to Back whether it is Front Past Full Capacity
    @Test(timeout = TIMEOUT)
    public void addToBackPastFullCapacity() {
        assertEquals(0, list.size());
        //int initialSize = list.getBackingArray().length;

        for (int i = 0; i < ArrayList.INITIAL_CAPACITY + 1; i++) {
            String temp = i + "a";
            list.addToBack(temp);
        }
    }

    //Test if remove at Index out of bounds negative
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexIndexOutOfBoundsNegative() {
        list.removeAtIndex(-6);
    }

    //Test if remove at Index out of bounds above the size
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAtRemoveIndexIndexOutOfBoundsAboveSize() {
        //list is empty so only acceptable index is 0
        list.removeAtIndex(3);
    }

    //TODO add enough to resize, then remove things below intial capacity to test that the backing array does not shrink
    @Test(timeout = TIMEOUT)
    public void testRemoveDoesNotShrinkBackingArray() {
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 2; i++) {
            String temp = i + "a";
            list.addToFront(temp);
        }
        // checks to see all elements have been added
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, list.size());
        // checks to see if the backing array capacity is doubled
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, ((Object[]) list.getBackingArray()).length);
        while (list.size() > 0) {
            list.removeFromBack();
        }
        // checks list is empty
        assertEquals(0, list.size());
        // checks that the back array capacity has not decreased
        assertEquals(ArrayList.INITIAL_CAPACITY * 2, ((Object[]) list.getBackingArray()).length);
    }

    //Test if remove from Front of empty list
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromFrontOfEmptyList() {
        list.removeFromFront();
    }

    //Test if remove from Back of empty list
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromBackOnEmptyList() {
        list.removeFromBack();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetIndexNegative() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        list.get(-1);
    }
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanSize() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        list.get(6);
    }

    @Test(timeout = TIMEOUT)
    public void testEmptyAfterAddingAndRemovingFromBack() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());

        assertSame("4a", list.removeFromBack()); // 0a, 1a, 2a, 3a
        assertSame("3a", list.removeFromBack()); // 0a, 1a, 2a
        assertSame("2a", list.removeFromBack()); // 0a, 1a
        assertSame("1a", list.removeFromBack()); // 0a
        assertSame("0a", list.removeFromBack()); // empty list

        assertTrue(list.isEmpty());

    }

    @Test(timeout = TIMEOUT)
    public void testEmptyAfterAddingAndRemovingFromFront() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());

        assertSame("0a", list.removeFromFront()); // 1a, 2a, 3a, 4a
        assertSame("1a", list.removeFromFront()); // 2a, 3a, 4a
        assertSame("2a", list.removeFromFront()); // 3a, 4a
        assertSame("3a", list.removeFromFront()); // 4a
        assertSame("4a", list.removeFromFront()); // empty list

        assertTrue(list.isEmpty());

    }

    @Test(timeout = TIMEOUT)
    public void testEmptyAfterAddingAndRemovingAtIndex() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());

        assertSame("3a", list.removeAtIndex(3)); // 0a, 1a, 2a, 4a
        assertSame("2a", list.removeAtIndex(2)); // 0a, 1a, 4a
        assertSame("4a", list.removeAtIndex(3)); // 0a, 1a
        assertSame("0a", list.removeAtIndex(0)); // 1a
        assertSame("1a", list.removeAtIndex(0)); // empty list

        assertTrue(list.isEmpty());

    }

}
