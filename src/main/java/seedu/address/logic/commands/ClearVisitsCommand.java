package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the VisitListPanel display by removing all visits from the filtered list.
 */
public class ClearVisitsCommand extends Command {

    public static final String COMMAND_WORD = "clearvisits";
    public static final String MESSAGE_SUCCESS = "Cleared all visits from the panel.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearVisits();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
