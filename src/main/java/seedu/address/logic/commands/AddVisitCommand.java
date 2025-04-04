package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Adds a visit to the Med Logger.
 */
public class AddVisitCommand extends Command {
    public static final String COMMAND_WORD = "visit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a visit to MedLogger.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + "[" + PREFIX_DATE + "DATE_TIME] "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_SYMPTOM + "SYMPTOM "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS "
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_FOLLOWUP + "FOLLOWUP\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_REMARK + "Headache "
            + PREFIX_SYMPTOM + "Headache "
            + PREFIX_DIAGNOSIS + "Migraine "
            + PREFIX_MEDICATION + "Panadol "
            + PREFIX_FOLLOWUP + "In 1 week";

    public static final String MESSAGE_SUCCESS = "New visit recorded: %1$s";
    public static final String MESSAGE_DUPLICATE_VISIT = "This visit already exists in the MedLogger";
    public static final String MESSAGE_NO_PERSON_FOR_VISIT = "The person in this visit does not exist in the MedLogger";

    private Visit visit;

    /**
     * Creates an AddVisitCommand to add the specified {@code Visit}
     */
    public AddVisitCommand(Visit visit) {
        requireNonNull(visit);
        this.visit = visit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Person> optionalPerson = model.getPersonByNric(visit.getNric());

        if (optionalPerson.isEmpty()) {
            throw new CommandException(MESSAGE_NO_PERSON_FOR_VISIT);
        }

        Person person = optionalPerson.get();

        // Replace the visit's person with the actual one from model
        this.visit = new Visit(
                person,
                visit.getDateTime(),
                visit.getRemark(),
                visit.getSymptom(),
                visit.getDiagnosis(),
                visit.getMedication(),
                visit.getFollowUp()
        );

        if (model.hasVisit(visit)) {
            throw new CommandException(MESSAGE_DUPLICATE_VISIT);
        }

        model.addVisit(visit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(visit)));
    }
}
