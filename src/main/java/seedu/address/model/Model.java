package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Visit> PREDICATE_SHOW_ALL_VISITS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Med Logger file path.
     */
    Path getMedLoggerFilePath();

    /**
     * Sets the user prefs' Med Logger file path.
     */
    void setMedLoggerFilePath(Path medLoggerFilePath);

    /**
     * Replaces Med Logger data with the data in {@code medLogger}.
     */
    void setMedLogger(ReadOnlyMedLogger medLogger);

    /**
     * Clears visit data in Med logger
     */
    void clearVisits();


    /** Returns the MedLogger */
    ReadOnlyMedLogger getMedLogger();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Med Logger.
     */
    boolean hasPerson(Person person);

    /**
     * Retrieves a {@code Person} with the specified NRIC.
     * If a {@code Person} is found, it is returned wrapped in an
     * {@code Optional}; otherwise, an empty {@code Optional} is returned.
     */
    Optional<Person> getPersonByNric(Nric nric);

    /**
     * Returns true if a vist with the same details as {@code vist} exists in the Med Logger.
     */
    boolean hasVisit(Visit visit);

    void addVisit(Visit visit);

    /**
     * Deletes the given visit.
     * The visit must exist in the Medlogger
     */
    void deleteVisit(Visit target);

    void setVisit(Visit target, Visit editedVisit);

    /**
     * Deletes the given person.
     * The person must exist in the Med Logger.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the Med Logger.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the Med Logger.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the Med Logger.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered visit list
     */
    ObservableList<Visit> getFilteredVisitList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered visit list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVisitList(Predicate<Visit> predicate);

    void sortFilteredVisitList(Comparator<Visit> comparator);

    /**
     * Updates the filter of the filtered person list up to the first n entries.
     * @param n number of entries to show
     */
    void updateSubFilteredPersonList(int n);

    /**
     * Updates the filter of the filtered visit list up to the first n entries.
     * @param n number of entries to show
     */
    void updateSubFilteredVisitList(int n);

    ObservableList<Visit> getSortedVisitList();


    int size();
}
