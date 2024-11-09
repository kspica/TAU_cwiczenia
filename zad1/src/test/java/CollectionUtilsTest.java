import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class CollectionUtilsTest {

    @Test
    void addElementTest() {
        List<String> dummyList = new ArrayList<>();
        CollectionUtils.addElement(dummyList, "Test");
        assertFalse(dummyList.isEmpty());
        assertEquals(1, dummyList.size());
    }

    @Test
    void removeElementTest() {
        List<String> dummyList = new ArrayList<>();
        CollectionUtils.addElement(dummyList, "Test");
        CollectionUtils.removeElement(dummyList, "Test");
        assertTrue(dummyList.isEmpty());
    }

    @Test
    void getUniqueElementsTest() {
        List<String> dummyList = Arrays.asList("Test", "Test", "Test");
        assertEquals(3, dummyList.size());
        Set<String> unique = CollectionUtils.getUniqueElements(dummyList);
        assertEquals(1, unique.size());
    }

    @Test
    void removeDuplicatesTest() {
        List<String> dummyList = Arrays.asList("Test", "Test", "Test", "Karol", "Karol");
        assertEquals(5, dummyList.size());
        Set<String> unique = CollectionUtils.getUniqueElements(dummyList);
        assertEquals(2, unique.size());
    }
}