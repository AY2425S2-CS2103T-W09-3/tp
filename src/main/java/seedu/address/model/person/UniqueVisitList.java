package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateVisitException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.VisitNotFoundException;

/**
 * A list of visits that enforces uniqueness between its elements and does not allow nulls.
 * A visit is considered unique by comparing using {@code Visit#equals(Object)}. As such, adding and updating of
 * visits uses Visit#equals(Object) for equality to ensure that the visit being added or updated is
 * unique in terms of identity in the UniqueVisitList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Visit#equals(Object)
 */
public class UniqueVisitList implements Iterable<Visit> {

    private final ObservableList<Visit> internalList = FXCollections.observableArrayList();
    private final ObservableList<Visit> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent visit as the given argument.
     */
    public boolean contains(Visit toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a visit to the list.
     */
    public void add(Visit toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateVisitException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the visit {@code target} in the list with {@code editedVisit}.
     * {@code target} must exist in the list.
     * The {@code editedVisit} must not be the same as another existing visit in the list.
     */
    public void setVisit(Visit target, Visit editedVisit) {
        requireAllNonNull(target, editedVisit);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new VisitNotFoundException();
        }

        if (!target.equals(editedVisit) && contains(editedVisit)) {
            throw new DuplicateVisitException();
        }

        internalList.set(index, editedVisit);
    }

    /**
     * Removes the equivalent visit from the list.
     * The viist must exist in the list.
     */
    public void remove(Visit toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code visits}.
     * {@code visits} must not contain duplicate visits.
     */
    public void setVisits(List<Visit> visits) {
        requireAllNonNull(visits);
        if (!visitsAreUnique(visits)) {
            throw new DuplicateVisitException();
        }

        internalList.setAll(visits);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Visit> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Visit> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniqueVisitList otherVisitList = (UniqueVisitList) other;
        return internalList.equals(otherVisitList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code visits} contains only unique visits.
     */
    private boolean visitsAreUnique(List<Visit> visits) {
        for (int i = 0; i < visits.size() - 1; i++) {
            for (int j = i + 1; j < visits.size(); j++) {
                if (visits.get(i).equals(visits.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
