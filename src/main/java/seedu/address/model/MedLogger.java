package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonVisitDictionary;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueVisitList;
import seedu.address.model.person.Visit;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MedLogger implements ReadOnlyMedLogger {

    private static final String MESSAGE_NO_PERSON_FOR_VISIT = "There is no person who have a matching profile to "
            + "this visit in the person list";
    private final UniquePersonList persons;
    private final UniqueVisitList visits;
    private final PersonVisitDictionary dictionary;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        visits = new UniqueVisitList();
        dictionary = new PersonVisitDictionary();
    }

    public MedLogger() {}

    /**
     * Creates an MedLogger using the Persons in the {@code toBeCopied}
     */
    public MedLogger(ReadOnlyMedLogger toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the visit list with {@code visits}.
     * {@code visits} must not contain duplicate persons.
     */
    public void setVisits(List<Visit> visits) {
        this.visits.setVisits(visits);
    }

    /**
     * Replaces the contents of the dictionary with {@code dictionary}.
     */
    public void setDictionary(PersonVisitDictionary dictionary) {
        this.dictionary.setDictionary(dictionary.getDictionary());
    }

    /**
     * Resets the existing data of this {@code MedLogger} with {@code newData}.
     */
    public void resetData(ReadOnlyMedLogger newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setVisits(newData.getVisitList());
        setDictionary(newData.getDictionary());
    }

    /**
     * Returns true if  {@code visit} already exists in the list of visits.
     */
    public boolean hasVisit(Visit visit) {
        requireNonNull(visit);
        return this.visits.contains(visit);
    }

    /**
     * Adds a visit to the list of visits.
     * The visit must not already exist in the list.
     */
    public void addVisit(Visit visit) {
        requireNonNull(visit);
        this.visits.add(visit);
        this.dictionary.addVisit(visit);
    }

    /**
     * Replaces the given visit {@code target} in the list with {@code editedVisit}.
     * {@code target} must exist in the Med Logger.
     * The {@code editedVisit} must not be the same as another existing visit in the Med Logger.
     */
    public void setVisit(Visit target, Visit editedVisit) {
        requireNonNull(editedVisit);
        visits.setVisit(target, editedVisit);
        dictionary.setVisit(target, editedVisit);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the Med Logger.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the Med Logger.
     * The person must not already exist in the Med Logger.
     */
    public void addPerson(Person p) {
        persons.add(p);
        dictionary.addPerson(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the Med Logger.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the Med Logger.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        persons.setPerson(target, editedPerson);
        for (Visit visit : dictionary.getVisitsForPerson(target)) {
            visits.setVisit(visit, visit.withPerson(editedPerson));
        }
        dictionary.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code MedLogger}.
     * {@code key} must exist in the Med Logger.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        for (Visit visit : dictionary.getVisitsForPerson(key)) {
            visits.remove(visit);
        }
        dictionary.removePerson(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("visits", visits)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Visit> getVisitList() {
        return visits.asUnmodifiableObservableList();
    }

    @Override
    public PersonVisitDictionary getDictionary() {
        return dictionary;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedLogger)) {
            return false;
        }

        MedLogger otherMedLogger = (MedLogger) other;
        return persons.equals(otherMedLogger.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Retrieves a {@code Person} with the specified NRIC.
     * If a {@code Person} is found, it is returned wrapped in an
     * {@code Optional}; otherwise, an empty {@code Optional} is returned.
     */
    public Optional<Person> getPersonByNric(Nric nric) {
        return persons.getPersonByNric(nric);
    }
}
