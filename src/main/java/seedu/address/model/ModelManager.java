package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Represents the in-memory model of the Med Logger data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MedLogger medLogger;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given medLogger and userPrefs.
     */
    public ModelManager(ReadOnlyMedLogger medLogger, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(medLogger, userPrefs);

        logger.fine("Initializing with Med Logger: " + medLogger + " and user prefs " + userPrefs);

        this.medLogger = new MedLogger(medLogger);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.medLogger.getPersonList());
    }

    public ModelManager() {
        this(new MedLogger(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getMedLoggerFilePath() {
        return userPrefs.getMedLoggerFilePath();
    }

    @Override
    public void setMedLoggerFilePath(Path medLoggerFilePath) {
        requireNonNull(medLoggerFilePath);
        userPrefs.setMedLoggerFilePath(medLoggerFilePath);
    }

    //=========== MedLogger ================================================================================

    @Override
    public void setMedLogger(ReadOnlyMedLogger medLogger) {
        this.medLogger.resetData(medLogger);
    }

    @Override
    public ReadOnlyMedLogger getMedLogger() {
        return medLogger;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return medLogger.hasPerson(person);
    }

    @Override
    public boolean hasVisit(Visit visit) {
        requireNonNull(visit);
        return medLogger.hasVisit(visit);
    }

    @Override
    public void addVisit(Visit visit) {
        requireNonNull(visit);
        medLogger.addVisit(visit);
    }

    @Override
    public void deletePerson(Person target) {
        medLogger.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        medLogger.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        medLogger.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedMedLogger}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return medLogger.equals(otherModelManager.medLogger)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
