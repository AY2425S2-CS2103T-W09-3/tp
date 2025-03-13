package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMedLogger;

/**
 * A class to access MedLogger data stored as a json file on the hard disk.
 */
public class JsonMedLoggerStorage implements MedLoggerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMedLoggerStorage.class);

    private Path filePath;

    public JsonMedLoggerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMedLoggerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedLogger> readMedLogger() throws DataLoadingException {
        return readMedLogger(filePath);
    }

    /**
     * Similar to {@link #readMedLogger()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyMedLogger> readMedLogger(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableMedLogger> jsonMedLogger = JsonUtil.readJsonFile(
                filePath, JsonSerializableMedLogger.class);
        if (!jsonMedLogger.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMedLogger.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMedLogger(ReadOnlyMedLogger medLogger) throws IOException {
        saveMedLogger(medLogger, filePath);
    }

    /**
     * Similar to {@link #saveMedLogger(ReadOnlyMedLogger)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMedLogger(ReadOnlyMedLogger medLogger, Path filePath) throws IOException {
        requireNonNull(medLogger);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMedLogger(medLogger), filePath);
    }

}
