package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void extractJsonObjects_validJson_returnsParsedList() throws Exception {
        String json = """
            {
              "persons": [
                {"name": "Alice", "phone": "123"},
                {"name": "Bob", "phone": "456"}
              ]
            }
            """;

        List<Map<String, String>> result = FileUtil.extractJsonObjects(json, "persons");
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).get("name"));
        assertEquals("456", result.get(1).get("phone"));
    }

    @Test
    public void extractJsonObjects_arrayKeyNotFound_returnsEmptyList() throws Exception {
        String json = """
            {
              "contacts": []
            }
            """;

        List<Map<String, String>> result = FileUtil.extractJsonObjects(json, "persons");
        assertTrue(result.isEmpty());
    }

    @Test
    public void extractJsonObjects_handlesArrayValuesAsString() throws Exception {
        String json = """
            {
              "persons": [
                {"name": "Charlie", "tags": ["friend", "colleague"]}
              ]
            }
            """;

        List<Map<String, String>> result = FileUtil.extractJsonObjects(json, "persons");
        assertEquals("[\"friend\",\"colleague\"]", result.get(0).get("tags"));
    }

    @Test
    public void quote_noSpecialChars_returnsSameString() {
        assertEquals("hello", FileUtil.quote("hello"));
    }

    @Test
    public void quote_containsQuote_escapesQuoteCorrectly() {
        assertEquals("\"He said \"\"hello\"\"\"", FileUtil.quote("He said \"hello\""));
    }

}
