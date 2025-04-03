package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Visit;


/**
 * Edits the details of an existing visit in the Med Logger.
 */
public class EditVisitCommand extends Command {

    public static final String COMMAND_WORD = "editvisit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the visit identified "
            + "by the index number used in the displayed visit list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NRIC + "Nric] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_DATE + "2011-11-11 11:11";

    public static final String MESSAGE_EDIT_VISIT_SUCCESS = "Edited Visit: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_VISIT = "This visit already exists in the Med Logger.";
    public static final String MESSAGE_NO_PERSON_FOR_VISIT = "The person in the edited "
            + "visit does not exist in the MedLogger";


    private final Index index;
    private final EditVisitDescriptor editVisitDescriptor;

    /**
     * @param index of the visit in the filtered visit list to edit
     * @param editVisitDescriptor details to edit the visit with
     */
    public EditVisitCommand(Index index, EditVisitDescriptor editVisitDescriptor) {
        requireNonNull(index);
        requireNonNull(editVisitDescriptor);

        this.index = index;
        this.editVisitDescriptor = new EditVisitDescriptor(editVisitDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Visit> lastShownList = model.getFilteredVisitList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VISIT_DISPLAYED_INDEX);
        }

        Visit visitToEdit = lastShownList.get(index.getZeroBased());
        Person person = visitToEdit.getPerson();

        if (editVisitDescriptor.getNric().isPresent()) {
            if (model.getPersonByNric(editVisitDescriptor.getNric().get()).isEmpty()) {
                throw new CommandException(MESSAGE_NO_PERSON_FOR_VISIT);
            }
            person = model.getPersonByNric(editVisitDescriptor.getNric().get()).get();
        }

        Visit editedVisit = createEditedVisit(person, visitToEdit, editVisitDescriptor);

        if (!visitToEdit.equals(editedVisit) && model.hasVisit(editedVisit)) {
            throw new CommandException(MESSAGE_DUPLICATE_VISIT);
        }

        model.setVisit(visitToEdit, editedVisit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_VISIT_SUCCESS, Messages.format(editedVisit)));
    }

    /**
     * Creates and returns a {@code Visit} with the details of {@code visitToEdit}
     * edited with {@code editVisitDescriptor}.
     */
    private static Visit createEditedVisit(Person person, Visit visitToEdit,
                                           EditVisitDescriptor editVisitDescriptor) {
        assert visitToEdit != null;

        DateTime updatedDateTime = editVisitDescriptor.getDateTime().orElse(visitToEdit.getDateTime());
        Remark updatedRemark = editVisitDescriptor.getRemark().orElse(visitToEdit.getRemark());

        return new Visit(person, updatedDateTime, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        EditVisitCommand otherEditVisitCommand = (EditVisitCommand) other;
        return index.equals(otherEditVisitCommand.index)
                && editVisitDescriptor.equals(otherEditVisitCommand.editVisitDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editVisitDescriptor", editVisitDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditVisitDescriptor {
        private Nric nric;
        private DateTime dateTime;
        private Remark remark;

        public EditVisitDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditVisitDescriptor(EditVisitDescriptor toCopy) {
            setNric(toCopy.nric);
            setDateTime(toCopy.dateTime);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(nric, dateTime, remark);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVisitDescriptor)) {
                return false;
            }

            EditVisitDescriptor otherEditVisitDescriptor = (EditVisitDescriptor) other;
            return Objects.equals(nric, otherEditVisitDescriptor.nric)
                    && Objects.equals(dateTime, otherEditVisitDescriptor.dateTime)
                    && Objects.equals(remark, otherEditVisitDescriptor.remark);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("NRIC", nric)
                    .add("dateTime", dateTime)
                    .add("remark", remark)
                    .toString();
        }
    }
}
