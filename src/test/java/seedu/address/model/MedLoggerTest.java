package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalMedLogger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonVisitDictionary;
import seedu.address.model.person.Visit;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class MedLoggerTest {

    private final MedLogger medLogger = new MedLogger();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), medLogger.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medLogger.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMedLogger_replacesData() {
        MedLogger newData = getTypicalMedLogger();
        medLogger.resetData(newData);
        assertEquals(newData, medLogger);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        MedLoggerStub newData = new MedLoggerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> medLogger.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medLogger.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInMedLogger_returnsFalse() {
        assertFalse(medLogger.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInMedLogger_returnsTrue() {
        medLogger.addPerson(ALICE);
        assertTrue(medLogger.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInMedLogger_returnsTrue() {
        medLogger.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(medLogger.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> medLogger.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = MedLogger.class.getCanonicalName() + "{persons=" + medLogger.getPersonList()
                + ", visits=" + medLogger.getVisitList() + "}";
        assertEquals(expected, medLogger.toString());
    }

    /**
     * A stub ReadOnlyMedLogger whose persons list can violate interface constraints.
     */
    private static class MedLoggerStub implements ReadOnlyMedLogger {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Visit> visits = FXCollections.observableArrayList();
        private final PersonVisitDictionary dictionary = new PersonVisitDictionary();

        MedLoggerStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Visit> getVisitList() {
            return visits;
        }

        @Override
        public PersonVisitDictionary getDictionary() {
            return dictionary;
        }
    }
}
