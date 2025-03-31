package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ExportCommandTest {

    /**
     * The ExportCommand class is UI-dependent, so the execute function is not tested.
     */

    @Test
    public void equals_sameFileType_returnsTrue() {
        ExportCommand a = new ExportCommand("json");
        ExportCommand b = new ExportCommand("json");
        assertEquals(a, b);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        ExportCommand a = new ExportCommand("csv");
        ExportCommand b = new ExportCommand("json");
        assertNotEquals(a, b);
    }

    @Test
    public void equals_duplicateFileType_returnsTrue() {
        ExportCommand a = new ExportCommand("json");
        ExportCommand b = new ExportCommand("json json");
        assertNotEquals(a, b);
    }
}
