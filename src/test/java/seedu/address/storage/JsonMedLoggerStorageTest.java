package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalMedLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.MedLogger;
import seedu.address.model.ReadOnlyMedLogger;

public class JsonMedLoggerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMedLoggerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMedLogger_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMedLogger(null));
    }

    private java.util.Optional<ReadOnlyMedLogger> readMedLogger(String filePath) throws Exception {
        return new JsonMedLoggerStorage(Paths.get(filePath)).readMedLogger(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMedLogger("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readMedLogger("notJsonFormatMedLogger.json"));
    }

    @Test
    public void readMedLogger_invalidPersonMedLogger_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMedLogger("invalidPersonMedLogger.json"));
    }

    @Test
    public void readMedLogger_invalidAndValidPersonMedLogger_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMedLogger("invalidAndValidPersonMedLogger.json"));
    }

    @Test
    public void readAndSaveMedLogger_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedLogger.json");
        MedLogger original = getTypicalMedLogger();
        JsonMedLoggerStorage jsonMedLoggerStorage = new JsonMedLoggerStorage(filePath);

        // Save in new file and read back
        jsonMedLoggerStorage.saveMedLogger(original, filePath);
        ReadOnlyMedLogger readBack = jsonMedLoggerStorage.readMedLogger(filePath).get();
        assertEquals(original, new MedLogger(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonMedLoggerStorage.saveMedLogger(original, filePath);
        readBack = jsonMedLoggerStorage.readMedLogger(filePath).get();
        assertEquals(original, new MedLogger(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonMedLoggerStorage.saveMedLogger(original); // file path not specified
        readBack = jsonMedLoggerStorage.readMedLogger().get(); // file path not specified
        assertEquals(original, new MedLogger(readBack));

    }

    @Test
    public void saveMedLogger_nullMedLogger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedLogger(null, "SomeFile.json"));
    }

    /**
     * Saves {@code medLogger} at the specified {@code filePath}.
     */
    private void saveMedLogger(ReadOnlyMedLogger medLogger, String filePath) {
        try {
            new JsonMedLoggerStorage(Paths.get(filePath))
                    .saveMedLogger(medLogger, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMedLogger_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedLogger(new MedLogger(), null));
    }
}
