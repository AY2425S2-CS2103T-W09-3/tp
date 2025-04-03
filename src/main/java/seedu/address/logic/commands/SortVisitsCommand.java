package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        ObservableList<Visit> currentList = FXCollections.observableArrayList(model.getFilteredVisitList());

        Comparator<Visit> comparator = (v1, v2) ->
            v1.getDateTime().toString().compareTo(v2.getDateTime().toString());
        if (isDescending) {
            comparator = comparator.reversed();
        }

        currentList.sort(comparator);
        model.updateFilteredVisitList(currentList::contains);

        return new CommandResult(isDescending ? MESSAGE_SUCCESS_DESC : MESSAGE_SUCCESS_ASC);
    }
}


