package seedu.address.logic.parser;

import seedu.address.logic.commands.SortVisitsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses arguments for the sortVisits command.
 */
public class SortVisitsCommandParser implements Parser<SortVisitsCommand> {

    private static final String ASC = "";
    private static final String DESC = "desc";

    @Override
    public SortVisitsCommand parse(String args) throws ParseException {
        String trimmed = args.trim().toLowerCase();

        if (trimmed.equals(ASC)) {
            return new SortVisitsCommand(false); // Ascending
        } else if (trimmed.equals(DESC)) {
            return new SortVisitsCommand(true); // Descending
        } else {
            throw new ParseException("Invalid sort direction. Use either:\n"
                + "  sortvisits        (ascending)\n"
                + "  sortvisits desc   (descending)");
        }
    }
}
