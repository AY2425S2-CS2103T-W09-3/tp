package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ListVisitsCommand extends Command {

    public static final String COMMAND_WORD = "listVisits";
    public static final String MESSAGE_SUCCESS = "Listed all visits.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVisitList(visit -> true); // show everything
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
