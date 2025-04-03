package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Nric;

/**
 * Lists all visits in the Med Logger to the user.
 */
public class ListVisitsCommand extends Command {

    public static final String COMMAND_WORD = "listvisits";
    public static final String MESSAGE_SUCCESS = "Listed all visits";
    public static final String MESSAGE_USAGE = "listvisits [i/NRIC] [l/LIMIT]";

    private final Nric nric;
    private final Integer limit;

    /**
     * Creates a ListVisitsCommand to list all visits.
     * thus no NRIC or limit is provided.
     */
    public ListVisitsCommand() {
        this.nric = null;
        this.limit = null;
    }

    /**
     * Creates a ListVisitsCommand to list only visits
     * with the specified NRIC and limit.
     * Both parameters are optional.
     *
     * @param nric the NRIC of the person whose visits to list
     * @param limit the maximum number of visits to list
     */
    public ListVisitsCommand(Nric nric, Integer limit) {
        this.nric = nric;
        this.limit = limit;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Apply NRIC filter if provided
        if (nric != null) {
            model.updateFilteredVisitList(visit -> visit.getNric().equals(nric));
        } else {
            model.updateFilteredVisitList(v -> true);
        }

        // Apply limit if provided
        if (limit != null) {
            model.updateSubFilteredVisitList(limit); // assumes sub-listing is after main filtering
            return new CommandResult("Listed " + Math.min(limit, model.getFilteredVisitList().size()) + " visits");
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
