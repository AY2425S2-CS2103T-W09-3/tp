package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Visit;

/**
 * Deletes a visit identified using it's displayed index from the Med Logger.
 */
public class DeleteVisitCommand extends Command {
    public static final String COMMAND_WORD = "deletevisit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the visit identified by the index number used in the displayed visit list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Deleted Visit: %1$s";

    private final Index targetIndex;

    public DeleteVisitCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Visit> lastShownList = model.getFilteredVisitList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VISIT_DISPLAYED_INDEX);
        }
        Visit visitToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteVisit(visitToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_VISIT_SUCCESS, Messages.format(visitToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteVisitCommand)) {
            return false;
        }

        DeleteVisitCommand otherDeleteVisitCommand = (DeleteVisitCommand) other;
        return targetIndex.equals(otherDeleteVisitCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

}
