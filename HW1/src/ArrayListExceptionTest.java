import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a set of Unit Tests to make sure all expected exceptions 
 * are thrown according to the instructions.
 *
 * NOTE: I also test the specific message the Exceptions thrown are
 * what I expect, you will have to change those with what you message
 * you throw your Exception with.
 *
 * @author Abhinav Vemulapalli
 * @version 1.0
 */
public class ArrayListExceptionTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testVariousAddMethods() {
        list.addToFront("1a"); // 1a
        list.addToBack("4a"); // 1a 4a
        list.addToFront("0a"); // 0a 1a 4a
        list.addAtIndex(2, "2a"); // 0a 1a 2a 4a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";

        assertArrayEquals(expected, list.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void testExceptionsThrownByAddAtIndex() {
        // Preliminary Setup
        list.addToBack("3a");
        list.addToFront("2a");
        list.addToFront("1a");
        list.addToFront("0a");

        // Testing IllegalArgumentException is thrown properly
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(4, null);
        });

        // Ensuring that the right IndexOutOfBoundsExceptions are being thrown with the proper message
        IndexOutOfBoundsException exception = Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(-1, "-1a");
        });
        String expectedMessage = "The entered index is less than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        exception = Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(5, "5a");
        });
        expectedMessage = "The provided index 5 is greater than the Array List's size of 4";
        actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test(timeout = TIMEOUT)
    public void testExceptionsThrownByAddToFrontAndBack() {
        // Preliminary Setup
        list.addToBack("3a");
        list.addToFront("2a");
        list.addToFront("1a");
        list.addToFront("0a");

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testExceptionsThrownByRemoveAtIndex() {
        // Preliminary Setup
        list.addToBack("3a");
        list.addToFront("2a");
        list.addToFront("1a");
        list.addToFront("0a");

        // Ensuring that the right IndexOutOfBoundsExceptions are being thrown with the proper message
        IndexOutOfBoundsException exception = Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(-1);
        });
        String expectedMessage = "The entered index is less than 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        exception = Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(5);
        });
        expectedMessage = "The provided index 5 is greater than the Array List's size of 4";
        actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test(timeout = TIMEOUT)
    public void testNoSuchElementException() {
        NoSuchElementException exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            list.removeFromBack();
        });

        String expectedMessage = "The list is empty, so there is nothing to remove";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            list.removeFromBack();
        });

        expectedMessage = "The list is empty, so there is nothing to remove";
        actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}