package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.MedLogger;
import seedu.address.model.ReadOnlyMedLogger;

/**
 * Represents a storage for {@link MedLogger}.
 */
public interface MedLoggerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMedLoggerFilePath();

    /**
     * Returns MedLogger data as a {@link ReadOnlyMedLogger}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyMedLogger> readMedLogger() throws DataLoadingException;

    /**
     * @see #getMedLoggerFilePath()
     */
    Optional<ReadOnlyMedLogger> readMedLogger(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyMedLogger} to the storage.
     * @param medLogger cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedLogger(ReadOnlyMedLogger medLogger) throws IOException;

    /**
     * @see #saveMedLogger(ReadOnlyMedLogger)
     */
    void saveMedLogger(ReadOnlyMedLogger medLogger, Path filePath) throws IOException;

}
