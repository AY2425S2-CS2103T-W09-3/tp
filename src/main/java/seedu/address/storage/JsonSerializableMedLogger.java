package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MedLogger;
import seedu.address.model.ReadOnlyMedLogger;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * An Immutable MedLogger that is serializable to JSON format.
 */
@JsonRootName(value = "addressddbook")
class JsonSerializableMedLogger {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_VISIT = "Visits list contains duplicate visit(s).";
    public static final String MESSAGE_NO_PERSON = "Some visits have no associated person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedVisit> visits = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMedLogger} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMedLogger(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                     @JsonProperty("visits") List<JsonAdaptedVisit> visits) {
        this.persons.addAll(persons);
        this.visits.addAll(visits);
    }

    /**
     * Converts a given {@code ReadOnlyMedLogger} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMedLogger}.
     */
    public JsonSerializableMedLogger(ReadOnlyMedLogger source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        visits.addAll(source.getVisitList().stream().map(JsonAdaptedVisit::new).collect(Collectors.toList()));
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

        for (JsonAdaptedVisit jsonAdaptedVisit : visits) {
            Nric nric = jsonAdaptedVisit.getNric();
            Optional<Person> optionalPerson = medLogger.getPersonByNric(nric);
            if (optionalPerson.isEmpty()) {
                throw new IllegalValueException(MESSAGE_NO_PERSON);
            }
            Visit visit = jsonAdaptedVisit.toModelType(optionalPerson.get());
            if (medLogger.hasVisit(visit)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VISIT);
            }
            medLogger.addVisit(visit);
        }

        return medLogger;
    }

}
