package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all visits in the Med Logger to the user.
 */
public class ListVisitsCommand extends Command {

    public static final String COMMAND_WORD = "listvisits";

    public static final String MESSAGE_SUCCESS = "Listed all visits";

    public static final String MESSAGE_USAGE = "listvisits l/LIMIT";

    private Integer limit;

    public ListVisitsCommand() {}

    public ListVisitsCommand(int limit) {
        this.limit = limit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (this.limit == null) {
            model.updateFilteredVisitList(visit -> true); // Show all
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.updateSubFilteredVisitList(this.limit);
            int numOfVisits = Math.min(this.limit, model.getFilteredVisitList().size());
            return new CommandResult("Listed " + numOfVisits + " visits");
        }
    }
}

