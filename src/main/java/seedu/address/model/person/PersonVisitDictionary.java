package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicateVisitException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.VisitNotFoundException;

/**
 * A dictionary that maps each person to their visits.
 * Supports a minimal set of list operations.
 */
public class PersonVisitDictionary {
    private final Map<Person, List<Visit>> personToVisits = new HashMap<>();

    /**
     * Replaces the contents of this map with {@code personsToVisits}.
     */
    public void setDictionary(Map<Person, List<Visit>> personToVisits) {
        requireNonNull(personToVisits);
        this.personToVisits.clear();
        for (Map.Entry<Person, List<Visit>> entry : personToVisits.entrySet()) {
            this.personToVisits.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    /**
     * Adds a new person.
     */
    public void addPerson(Person person) {
        requireNonNull(person);
        if (personToVisits.containsKey(person)) {
            throw new DuplicatePersonException();
        }
        personToVisits.put(person, new ArrayList<>());
    }

    /**
     * Adds a visit to its attached person.
     */
    public void addVisit(Visit visit) {
        requireNonNull(visit);
        Person person = visit.getPerson();
        if (!personToVisits.containsKey(person)) {
            throw new PersonNotFoundException();
        }
        if (personToVisits.get(person).contains(visit)) {
            throw new DuplicateVisitException();
        }
        personToVisits.get(person).add(visit);
    }

    public void setVisit(Visit target, Visit editedVisit) {
        requireAllNonNull(target, editedVisit);
        if (target.getPerson().equals(editedVisit.getPerson())) {
            if (!personToVisits.containsKey(target.getPerson())) {
                throw new PersonNotFoundException();
            }
            personToVisits.get(target.getPerson()).add(editedVisit);
            personToVisits.get(target.getPerson()).remove(target);
        } else {
            if (!personToVisits.containsKey(target.getPerson())
                    || !personToVisits.containsKey(editedVisit.getPerson())) {
                throw new PersonNotFoundException();
            }
            personToVisits.get(target.getPerson()).remove(target);
            personToVisits.get(editedVisit.getPerson()).add(editedVisit);
        }
    }

    /**
     * Removes a visit.
     */
    public void removeVisit(Visit visit) {
        requireNonNull(visit);
        Person person = visit.getPerson();
        if (!personToVisits.containsKey(person)) {
            throw new PersonNotFoundException();
        }
        if (!personToVisits.get(person).contains(visit)) {
            throw new VisitNotFoundException();
        }
        personToVisits.get(person).remove(visit);
    }

    /**
     * Replaces a person with its edited version.
     * Migrates their visits to the edited version.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        if (!personToVisits.containsKey(target)) {
            throw new PersonNotFoundException();
        }
        List<Visit> targetPersonVisits = personToVisits.get(target);
        personToVisits.remove(target);
        addPerson(editedPerson);
        for (Visit visit : targetPersonVisits) {
            personToVisits.get(editedPerson).add(visit.withPerson(editedPerson));
        }
    }

    /**
     * Removes a person together with their visits.
     */
    public void removePerson(Person person) {
        requireNonNull(person);
        if (!personToVisits.containsKey(person)) {
            throw new PersonNotFoundException();
        }
        personToVisits.remove(person);
    }

    public List<Visit> getVisitsForPerson(Person person) {
        requireNonNull(person);
        return Collections.unmodifiableList(
                personToVisits.getOrDefault(person, Collections.emptyList())
        );
    }

    public Map<Person, List<Visit>> getDictionary() {
        return Collections.unmodifiableMap(this.personToVisits);
    }
}

