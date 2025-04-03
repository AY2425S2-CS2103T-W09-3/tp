package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Symptom;
import seedu.address.model.person.Visit;



/**
 * Parses input arguments and creates a new AddVisitCommand object
 */
public class AddVisitCommandParser implements Parser<AddVisitCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddVisitCommand
     * and returns an AddVisitCommand object for execution.
     * A dummy person instance is created to instantiate a visit
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVisitCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME, PREFIX_NRIC, PREFIX_DATE, PREFIX_REMARK,
                PREFIX_SYMPTOM, PREFIX_DIAGNOSIS, PREFIX_MEDICATION, PREFIX_FOLLOWUP
        );

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC, PREFIX_REMARK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVisitCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());

        // Optional date
        DateTime dateTime;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateInput = argMultimap.getValue(PREFIX_DATE).get();
            if (!DateTime.isValidDate(dateInput)) {
                throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
            }
            dateTime = new DateTime(dateInput);
        } else {
            dateTime = DateTime.now(); // Use current time if not specified
        }

        // Optional visit fields
        Symptom symptom = new Symptom(argMultimap.getValue(PREFIX_SYMPTOM).orElse("N/A"));
        Diagnosis diagnosis = new Diagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).orElse("N/A"));
        Medication medication = new Medication(argMultimap.getValue(PREFIX_MEDICATION).orElse("N/A"));
        FollowUp followUp = new FollowUp(argMultimap.getValue(PREFIX_FOLLOWUP).orElse("N/A"));

        Person dummyPerson = Person.createDummyPerson(name, nric);

        Visit visit = new Visit(dummyPerson, dateTime, remark, symptom, diagnosis, medication, followUp);
        return new AddVisitCommand(visit);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
