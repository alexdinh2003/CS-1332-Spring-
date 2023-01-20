import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayListTest {


    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    //test exceptions:

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionAddAtIndexSmaller() {
        list.addAtIndex(-1, "-1");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionAddAtIndexLarger() {
        list.addToBack("a");
        list.addToBack("b");
        list.addAtIndex(3, "d");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testExceptionAddAtIndexNull() {
        list.addToBack("a");
        list.addToBack("b");
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testExceptionAddToFrontNull() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testExceptionAddToBackNull() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionRemoveAtIndexSmaller() {
        list.addToBack("a");
        list.addToBack("b");
        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionRemoveAtIndexLarger() {
        list.addToBack("a");
        list.addToBack("b");
        list.removeAtIndex(list.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testExceptionRemoveFromFrontLarger() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testExceptionRemoveFromBackLarger() {
        list.removeFromBack();
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionGetSmaller() {
        list.addToBack("a");
        list.addToBack("b");
        list.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testExceptionGetLarger() {
        list.addToBack("a");
        list.addToBack("b");
        list.get(list.size());
    }

    //check whether the element at original size-1 is null after removing an element
    @Test(timeout = TIMEOUT)
    public void testGetNull() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");
        list.removeAtIndex(1);
        assertNull(((Object[]) (list.getBackingArray()))[3]);
    }

    //test double capacity
    @Test(timeout = TIMEOUT)
    public void testDoubleCapacity() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");
        list.addToBack("e");
        list.addToBack("f");
        list.addToBack("g");
        list.addToBack("h");
        list.addToBack("i");
        list.addToBack("doublelength!");
        assertEquals(list.INITIAL_CAPACITY * 2, ((Object[]) list.getBackingArray()).length);
    }


}