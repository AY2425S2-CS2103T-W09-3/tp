package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MedLogger;
import seedu.address.model.ReadOnlyMedLogger;
import seedu.address.model.person.Person;

/**
 * An Immutable MedLogger that is serializable to JSON format.
 */
@JsonRootName(value = "addressddbook")
class JsonSerializableMedLogger {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMedLogger} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMedLogger(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMedLogger} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMedLogger}.
     */
    public JsonSerializableMedLogger(ReadOnlyMedLogger source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Med Logger into the model's {@code MedLogger} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MedLogger toModelType() throws IllegalValueException {
        MedLogger medLogger = new MedLogger();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (medLogger.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            medLogger.addPerson(person);
        }
        return medLogger;
    }

}
