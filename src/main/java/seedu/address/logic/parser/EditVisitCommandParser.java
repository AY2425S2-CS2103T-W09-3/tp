package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOLLOWUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SYMPTOM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Symptom;

/**
 * Parses input arguments and creates a new EditVisitCommand object
 */
public class EditVisitCommandParser implements Parser<EditVisitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditVisitCommand
     * and returns an EditVisitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NRIC, PREFIX_DATE, PREFIX_REMARK,
                PREFIX_SYMPTOM, PREFIX_DIAGNOSIS, PREFIX_MEDICATION, PREFIX_FOLLOWUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditVisitCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_DATE, PREFIX_REMARK,
                PREFIX_SYMPTOM, PREFIX_DIAGNOSIS, PREFIX_MEDICATION, PREFIX_FOLLOWUP);

        EditVisitCommand.EditVisitDescriptor editVisitDescriptor = new EditVisitCommand.EditVisitDescriptor();

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            editVisitDescriptor.setNric(ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editVisitDescriptor.setDateTime(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editVisitDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_SYMPTOM).isPresent()) {
            editVisitDescriptor.setSymptom(new Symptom(argMultimap.getValue(PREFIX_SYMPTOM).get()));
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            editVisitDescriptor.setDiagnosis(new Diagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get()));
        }
        if (argMultimap.getValue(PREFIX_MEDICATION).isPresent()) {
            editVisitDescriptor.setMedication(new Medication(argMultimap.getValue(PREFIX_MEDICATION).get()));
        }
        if (argMultimap.getValue(PREFIX_FOLLOWUP).isPresent()) {
            editVisitDescriptor.setFollowUp(new FollowUp(argMultimap.getValue(PREFIX_FOLLOWUP).get()));
        }

        if (!editVisitDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditVisitCommand.MESSAGE_NOT_EDITED);
        }

        return new EditVisitCommand(index, editVisitDescriptor);
    }
}
