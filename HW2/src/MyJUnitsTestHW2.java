package src;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MyJUnitsTestHW2 {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        CircularSinglyLinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertEquals("1st", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2nd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3rd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("6th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("7th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("8th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("9th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("10th", current.getData());
        assertSame(list.getHead(), current.getNext());

    }

    @Test (timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("10th");
        list.addToFront("9th");
        list.addToFront("8th");
        list.addToFront("7th");
        list.addToFront("6th");
        list.addToFront("5th");
        list.addToFront("4th");
        list.addToFront("3rd");
        list.addToFront("2nd");
        list.addToFront("1st");

        assertEquals(10, list.size());

        CircularSinglyLinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertEquals("1st", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2nd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3rd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("6th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("7th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("8th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("9th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("10th", current.getData());
        assertSame(list.getHead(), current.getNext());
    }

    @Test (timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("1st");
        list.addToBack("2nd");
        list.addToBack("3rd");
        list.addToBack("4th");
        list.addToBack("5th");
        list.addToBack("6th");
        list.addToBack("7th");
        list.addToBack("8th");
        list.addToBack("9th");
        list.addToBack("10th");

        assertEquals(10, list.size());

        CircularSinglyLinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertEquals("1st", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2nd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3rd", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("6th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("7th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("8th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("9th", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("10th", current.getData());
        assertSame(list.getHead(), current.getNext());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        assertEquals("8th", list.removeAtIndex(7)); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 9th, 10th
        assertEquals("1st", list.removeAtIndex(0)); // 2nd, 3rd, 4th, 5th, 6th, 7th, 9th, 10th

        assertEquals(8, list.size());

        list.removeAtIndex(1); // 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.removeAtIndex(1); // 2nd, 5th, 6th, 7th, 9th, 10th
        list.removeAtIndex(1); // 2nd, 6th, 7th, 9th, 10th
        list.removeAtIndex(1); // 2nd, 7th, 9th, 10th
        list.removeAtIndex(1); // 2nd, 9th, 10th
        list.removeAtIndex(1); // 2nd, 10th

        assertEquals(2, list.size());
        assertEquals("2nd", list.getHead().getData());
        assertEquals("10th", list.getHead().getNext().getData());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        assertEquals("1st", list.removeFromFront()); // 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.removeFromFront(); // 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.removeFromFront(); // 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.removeFromFront(); // 5th, 6th, 7th, 8th, 9th, 10th
        list.removeFromFront(); // 6th, 7th, 8th, 9th, 10th
        list.removeFromFront(); // 7th, 8th, 9th, 10th
        list.removeFromFront(); // 8th, 9th, 10th
        list.removeFromFront(); // 9th, 10th

        list.addToFront("1st");
        assertEquals("1st", list.removeFromFront());

        assertEquals(2, list.size());
        assertEquals("9th", list.getHead().getData());
        assertEquals("10th", list.getHead().getNext().getData());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveFromBack() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        assertEquals("10th", list.removeFromBack()); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th

        list.removeFromBack(); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th
        list.removeFromBack(); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th
        list.removeFromBack(); // 1st, 2nd, 3rd, 4th, 5th, 6th
        list.removeFromBack(); // 1st, 2nd, 3rd, 4th, 5th
        list.removeFromBack(); // 1st, 2nd, 3rd, 4th
        list.removeFromBack(); // 1st, 2nd, 3rd
        list.removeFromBack(); // 1st, 2nd

        assertEquals(2, list.size());

        list.addToBack("3rd");
        assertEquals("3rd", list.removeFromBack());

        assertEquals(2, list.size());
        assertEquals(list.getHead().getData(), "1st");
        assertEquals("2nd", list.getHead().getNext().getData());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
    }

    @Test (timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals("4th", list.get(3));
        assertEquals("10th", list.get(list.size() - 1));
        assertEquals("10th", list.get(9));
        assertEquals("1st", list.get(0));
    }

    @Test (timeout = TIMEOUT)
    public void testIsEmpty() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertFalse(list.isEmpty());

        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();
        list.removeFromBack();

        assertFalse(list.isEmpty());

        list.removeFromBack();
        list.removeFromBack();

        assertTrue(list.isEmpty());

    }

    @Test (timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.getHead());

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrence() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(8, "8th"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 8th, 9th, 10th
        list.addToFront("1st"); // 1st, 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 8th, 9th, 10th

        assertEquals(12, list.size());

        assertEquals(list.get(9), list.removeLastOccurrence("8th"));
        list.removeLastOccurrence("1st");
        assertSame(list.getHead().getData(), list.removeLastOccurrence("1st"));


    }

    @Test (timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, "2nd"); // 2nd
        list.addAtIndex(1, "9th"); // 2nd, 9th
        list.addAtIndex(0, "1st"); // 1st, 2nd, 9th
        list.addAtIndex(2, "5th"); // 1st, 2nd, 5th, 9th
        list.addAtIndex(4, "10th"); // 1st, 2nd, 5th, 9th, 10th
        list.addAtIndex(3, "7th"); // 1st, 2nd, 5th, 7th, 9th, 10th
        list.addAtIndex(2, "4th"); // 1st, 2nd, 4th, 5th, 7th, 9th, 10th
        list.addAtIndex(4, "6th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 9th, 10th
        list.addAtIndex(6, "8th"); // 1st, 2nd, 4th, 5th, 6th, 7th, 8th, 9th, 10th
        list.addAtIndex(2, "3rd"); // 1st, 2nd, 3rd, 4th, 5th, 6th, 7th, 8th, 9th, 10th

        assertEquals(10, list.size());

        String[] duplicate = new String[10];
        duplicate[0] = "1st";
        duplicate[1] = "2nd";
        duplicate[2] = "3rd";
        for (int i = 3; i < 10; i++) {
            duplicate[i] = (i + 1) + "th";
        }

        assertArrayEquals(duplicate, list.toArray());

        list.clear();
        assertEquals(0, list.size());
        String[] empty = new String[0];
        assertArrayEquals(empty, list.toArray());


    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddIndexOutOfBounds() {
        list.addAtIndex(1, "1st");
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddIndexOutOfBoundsNeg() {
        list.addAtIndex(-1, "-1st");

    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBounds() {
        list.addAtIndex(0, "1st");
        assertEquals(1, list.size());
        list.removeAtIndex(2);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBoundsNeg() {
        list.addAtIndex(0, "1st");
        assertEquals(1, list.size());
        list.removeAtIndex(-1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds() {
        list.addAtIndex(0, "1st");
        list.get(1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBoundsNeg() {
        list.addAtIndex(0, "1st");
        list.get(-1);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddIndexIllegalArgument() {
        list.addAtIndex(0, null);

    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddFrontIllegalArgument() {
        list.addToFront(null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddBackIllegalArgument() {
        list.addToBack(null);
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveLastOccurrenceIllegalArgument() {
        list.addToBack("1st");
        list.addToBack("1st");

        list.removeLastOccurrence(null);
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFrontNoSuchElement() {
        assertEquals(0, list.size());
        list.removeFromFront();
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveBackNoSuchElement() {
        assertEquals(0, list.size());
        list.removeFromBack();
    }

    @Test (timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElement() {
        assertEquals(0, list.size());
        list.removeLastOccurrence("1st");
    }

}
