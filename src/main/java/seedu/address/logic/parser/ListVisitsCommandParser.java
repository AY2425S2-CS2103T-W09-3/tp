package seedu.address.logic.parser;

import seedu.address.logic.commands.ListVisitsCommand;

public class ListVisitsCommandParser implements Parser<ListVisitsCommand> {
    @Override
    public ListVisitsCommand parse(String args) {
        return new ListVisitsCommand();
    }
}
