package seedu.address.testutil;

import seedu.address.model.MedLogger;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code MedLogger ab = new MedLoggerBuilder().withPerson("John", "Doe").build();}
 */
public class MedLoggerBuilder {

    private MedLogger medLogger;

    public MedLoggerBuilder() {
        medLogger = new MedLogger();
    }

    public MedLoggerBuilder(MedLogger medLogger) {
        this.medLogger = medLogger;
    }

    /**
     * Adds a new {@code Person} to the {@code MedLogger} that we are building.
     */
    public MedLoggerBuilder withPerson(Person person) {
        medLogger.addPerson(person);
        return this;
    }

    public MedLogger build() {
        return medLogger;
    }
}
