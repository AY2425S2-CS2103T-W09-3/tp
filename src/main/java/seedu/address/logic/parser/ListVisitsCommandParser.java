package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.logic.commands.ListVisitsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_LIMIT);

        Nric nric = null;
        Integer limit = null;

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

        return new ListVisitsCommand(nric, limit);
    }
}
