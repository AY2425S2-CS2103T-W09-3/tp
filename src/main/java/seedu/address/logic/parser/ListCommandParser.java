package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ListCommand} object.
 */
public class ListCommandParser {

    /**
     * Parses the given {@code String} of arguments and returns a {@code ListCommand}.
     *
     * @param args Input string after command word.
     * @return A {@code ListCommand} based on the parsed input.
     * @throws ParseException If the input does not conform to the expected format.
     */
    public ListCommand parse(String args) throws ParseException {

        if (args.trim().isEmpty()) {
            return new ListCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LIMIT);

        int limit;

        try {
            limit = Integer.parseInt(argMultimap.getValue(PREFIX_LIMIT).orElse(""));
        } catch (NumberFormatException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), ive);
        }

        return new ListCommand(limit);
    }

}
