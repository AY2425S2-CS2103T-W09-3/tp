package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Unmodifiable view of an Med Logger
 */
public interface ReadOnlyMedLogger {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the visit list.
     */
    ObservableList<Visit> getVisitList();
}
