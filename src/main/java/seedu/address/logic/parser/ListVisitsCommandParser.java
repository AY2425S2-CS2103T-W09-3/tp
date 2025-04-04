package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODAY;

import java.time.LocalDate;

import seedu.address.logic.commands.ListVisitsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Symptom;

/**
 * Parses input arguments and creates a new {@code ListVisitsCommand} object.
 */
public class ListVisitsCommandParser {

    /**
     * Parses the given {@code String} of arguments and returns a {@code ListVisitsCommand}.
     *
     * @param args Input string after command word.
     * @return A {@code ListVisitsCommand} based on the parsed input.
     * @throws ParseException If the input does not conform to the expected format.
     */
    public ListVisitsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_LIMIT,
                PREFIX_FROM, PREFIX_TO, PREFIX_TODAY, PREFIX_SYMPTOM,
                PREFIX_DIAGNOSIS, PREFIX_MEDICATION);

        Nric nric = null;
        Integer limit = null;
        LocalDate from = null;
        LocalDate to = null;
        Diagnosis diagnosis = null;
        Symptom symptom = null;
        Medication medication = null;

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        }

        if (argMultimap.getValue(PREFIX_LIMIT).isPresent()) {
            try {
                limit = Integer.parseInt(argMultimap.getValue(PREFIX_LIMIT).get());
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListVisitsCommand.MESSAGE_USAGE), e);
            }
        }

        if (argMultimap.getValue(PREFIX_FROM).isPresent()) {
            from = LocalDate.parse(argMultimap.getValue(PREFIX_FROM).get());
        }
        if (argMultimap.getValue(PREFIX_TO).isPresent()) {
            to = LocalDate.parse(argMultimap.getValue(PREFIX_TO).get());
        }

        boolean isToday = argMultimap.getValue(PREFIX_TODAY).isPresent();

        if ((from != null || to != null) && isToday) {
            throw new ParseException("Cannot use both 'today/' and 'from/… to/…' together.");
        }

        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            diagnosis = ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get());
        }

        if (argMultimap.getValue(PREFIX_SYMPTOM).isPresent()) {
            symptom = ParserUtil.parseSymptom(argMultimap.getValue(PREFIX_SYMPTOM).get());
        }

        if (argMultimap.getValue(PREFIX_MEDICATION).isPresent()) {
            medication = ParserUtil.parseMedication(argMultimap.getValue(PREFIX_MEDICATION).get());
        }

        return new ListVisitsCommand(nric, limit, from, to, isToday, symptom, diagnosis, medication);
    }
}
