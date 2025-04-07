package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Nric;
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
    private final FilteredList<Visit> filteredVisits;
    private final SortedList<Visit> sortedVisits;


    /**
     * Initializes a ModelManager with the given medLogger and userPrefs.
     */
    public ModelManager(ReadOnlyMedLogger medLogger, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(medLogger, userPrefs);

        logger.fine("Initializing with Med Logger: " + medLogger + " and user prefs " + userPrefs);

        this.medLogger = new MedLogger(medLogger);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.medLogger.getPersonList());
        filteredVisits = new FilteredList<>(this.medLogger.getVisitList());
        sortedVisits = new SortedList<>(filteredVisits);
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
    public void clearVisits() {
        this.medLogger.clearVisits();
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
    public Optional<Person> getPersonByNric(Nric nric) {
        return medLogger.getPersonByNric(nric);
    }

    @Override
    public void addVisit(Visit visit) {
        requireNonNull(visit);
        medLogger.addVisit(visit);
        updateFilteredVisitList(PREDICATE_SHOW_ALL_VISITS);
    }

    @Override
    public void setVisit(Visit target, Visit editedVisit) {
        requireAllNonNull(target, editedVisit);
        medLogger.setVisit(target, editedVisit);
    }

    @Override
    public void deletePerson(Person target) {
        medLogger.removePerson(target);
    }

    @Override
    public void deleteVisit(Visit target) {
        medLogger.removeVisit(target);
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
    public ObservableList<Visit> getFilteredVisitList() {
        return filteredVisits;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredVisitList(Predicate<Visit> predicate) {
        requireNonNull(predicate);
        filteredVisits.setPredicate(predicate);
    }

    @Override
    public void updateSubFilteredPersonList(int n) {
        List<Person> limitedList = filteredPersons.getSource().stream()
                .filter(PREDICATE_SHOW_ALL_PERSONS)
                .limit(n)
                .collect(Collectors.toList());

        filteredPersons.setPredicate(limitedList::contains);
    }

    /**
     * Sorts the filtered visit list based on the provided comparator.
     * To force the filtered list to update, we create a new list and set the predicate to
     *
     * @param comparator The comparator to sort the visits.
     */
    @Override
    public void sortFilteredVisitList(Comparator<Visit> comparator) {
        sortedVisits.setComparator(comparator);
    }


    @Override
    public void updateSubFilteredVisitList(int n) {
        List<Visit> limitedList = filteredVisits.getSource().stream()
                .limit(n)
                .collect(Collectors.toList());

        filteredVisits.setPredicate(limitedList::contains);
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
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredVisits.equals(otherModelManager.filteredVisits);

    }

    @Override
    public ObservableList<Visit> getSortedVisitList() {
        return sortedVisits;
    }

    @Override
    public int size() {
        return filteredPersons.size();
    }
}
