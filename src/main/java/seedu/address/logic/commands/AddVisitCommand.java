package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Visit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

public class AddVisitCommand extends Command {
    public static final String COMMAND_WORD = "visit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Add a visit to MedLogger. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_REMARK + "REMARK"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_DATE + "2024-12-31 "
            + PREFIX_REMARK + "Headache";
    public static final String MESSAGE_SUCCESS = "New visit recorded: %1$s";
    public static final String MESSAGE_DUPLICATE_VISIT = "This visit already exists in the MedLogger";

    private final Visit visit;

    public AddVisitCommand(Visit visit) {
        requireNonNull(visit);
        this.visit = visit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasVisit(visit)) {
            throw new CommandException(MESSAGE_DUPLICATE_VISIT);
        }

        model.addVisit(visit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(visit)));
    }
}
