package homeworkJSONTest;

import homeworkJSON.JSONComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONComparatorTest {
    @Test
    public void testExactMatch() {
        String expected = "{\"name\": \"Emanuel\", \"age\": 30, \"isStudent\": false}";
        String actual = "{\"name\": \"Emanuel\", \"age\": 30, \"isStudent\": false}";

        boolean result = JSONComparator.compareJSONs(expected, actual);
        Assertions.assertTrue(result);
    }

    @Test
    public void testMismatch() {
        String expected = "{\"name\": \"Emanuel\", \"age\": \"ignore\", \"isStudent\": false}";
        String actual = "{\"name\": \"Emanuel\", \"age\": 25, \"isStudent\": true}";

        boolean result = JSONComparator.compareJSONs(expected, actual);
        Assertions.assertFalse(result);
    }
}
