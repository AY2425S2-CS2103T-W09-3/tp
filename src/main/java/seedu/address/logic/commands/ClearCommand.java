package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.MedLogger;
import seedu.address.model.Model;

/**
 * Clears the Med Logger.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Med Logger has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new MedLogger());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
