package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Visit;

/**
 * Sorts the currently visible visit list by visit dateTime.
 */
public class SortVisitsCommand extends Command {

    public static final String COMMAND_WORD = "sortvisits";
    public static final String MESSAGE_SUCCESS_ASC = "Sorted visits by date and time (ascending).";
    public static final String MESSAGE_SUCCESS_DESC = "Sorted visits by date and time (descending).";

    private final boolean isDescending;

    public SortVisitsCommand(boolean isDescending) {
        this.isDescending = isDescending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Comparator<Visit> comparator = Comparator.comparing((Visit v) -> v.getDateTime().toLocalDateTime());
        if (isDescending) {
            comparator = comparator.reversed();
        }

        model.sortFilteredVisitList(comparator);

        return new CommandResult(isDescending ? MESSAGE_SUCCESS_DESC : MESSAGE_SUCCESS_ASC);
    }
}


