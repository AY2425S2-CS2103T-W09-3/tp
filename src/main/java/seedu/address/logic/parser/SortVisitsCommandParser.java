package seedu.address.logic.parser;

import seedu.address.logic.commands.SortVisitsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses arguments for the sortVisits command.
 */
public class SortVisitsCommandParser implements Parser<SortVisitsCommand> {

    @Override
    public SortVisitsCommand parse(String args) throws ParseException {
        String trimmed = args.trim().toLowerCase();
        boolean isDescending = trimmed.equals("desc");
        return new SortVisitsCommand(isDescending);
    }
}
