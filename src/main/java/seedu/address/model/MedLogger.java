package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.Visit;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MedLogger implements ReadOnlyMedLogger {

    private static final String MESSAGE_NO_PERSON_FOR_VISIT = "There is no person who have a matching profile to "
            + "this visit in the person list";
    private final UniquePersonList persons;
    private final ArrayList<Visit> visits;
    private final HashMap<String, Person> personHashMap;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        visits = new ArrayList<>();
        personHashMap = new HashMap<>();
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
     * Resets the existing data of this {@code MedLogger} with {@code newData}.
     */
    public void resetData(ReadOnlyMedLogger newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
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
    public void addVisit(Visit visit) throws CommandException {
        requireNonNull(visit);
        String key = visit.getPerson().getNric().toString();
        if (personHashMap.containsKey(key)) {
            Person person = personHashMap.get(key);
            Visit modified = new Visit(person, visit.getDateTime(), visit.getRemark());
            this.visits.add(modified);
            person.addVisit(modified);
            person.showVisits();
        } else {
            throw new CommandException(MESSAGE_NO_PERSON_FOR_VISIT);
        }
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
        personHashMap.put(p.getNric().toString(), p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the Med Logger.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the Med Logger.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        personHashMap.remove(target.getNric().toString());
        personHashMap.put(editedPerson.getNric().toString(), editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code MedLogger}.
     * {@code key} must exist in the Med Logger.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        personHashMap.remove(key.getNric().toString());
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
}
