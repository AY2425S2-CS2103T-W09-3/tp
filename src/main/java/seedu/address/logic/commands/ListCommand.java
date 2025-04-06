package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the Med Logger to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = "list l/LIMIT";

    public static final String MESSAGE_LIMIT_CONSTRAINTS = "Limit must be non-negative integer";

    private Integer limit;

    public ListCommand() {};

    public ListCommand(int limit) {
        this.limit = limit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (this.limit == null) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            model.updateSubFilteredPersonList(this.limit);
            int numOfPersons = this.limit > model.size() ? model.size() : this.limit;
            return new CommandResult("Listed " + numOfPersons + " persons");
        }

    }
}
