package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Symptom;
import seedu.address.model.person.Visit;

/**
 * Lists all visits in the Med Logger to the user.
 */
public class ListVisitsCommand extends Command {

    public static final String COMMAND_WORD = "listvisits";
    public static final String MESSAGE_SUCCESS = "Listed all visits";
    public static final String MESSAGE_USAGE = "listvisits [i/NRIC] [l/LIMIT]";

    private final Nric nric;
    private final Integer limit;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final boolean isToday;
    private final Symptom symptom;
    private final Diagnosis diagnosis;
    private final Medication medication;


    /**
     * Creates a ListVisitsCommand to list all visits.
     * thus no NRIC or limit is provided.
     */
    public ListVisitsCommand() {
        this.nric = null;
        this.limit = null;
        this.fromDate = null;
        this.toDate = null;
        this.isToday = false;
        this.symptom = null;
        this.diagnosis = null;
        this.medication = null;
    }

    /**
     * Creates a ListVisitsCommand to list only visits
     * with the specified NRIC and limit.
     * Both parameters are optional.
     *
     * @param nric the NRIC of the person whose visits to list
     * @param limit the maximum number of visits to list
     */
    public ListVisitsCommand(Nric nric, Integer limit, LocalDate fromDate, LocalDate toDate,
            boolean isToday, Symptom symptom, Diagnosis diagnosis, Medication medication) {
        this.nric = nric;
        this.limit = limit;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.isToday = isToday;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.medication = medication;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Visit> predicate = v -> true;

        if (nric != null) {
            predicate = predicate.and(visit -> visit.getNric().equals(nric));
        }

        if (symptom != null) {
            predicate = predicate.and(visit ->
                visit.getSymptom().value.toLowerCase().contains(symptom.toLowerCase()));
        }

        if (diagnosis != null) {
            predicate = predicate.and(visit ->
                visit.getDiagnosis().value.toLowerCase().contains(diagnosis.toLowerCase()));
        }

        if (medication != null) {
            predicate = predicate.and(visit ->
                visit.getMedication().value.toLowerCase().contains(medication.toLowerCase()));
        }

        if (isToday) {
            LocalDate today = LocalDate.now();
            predicate = predicate.and(visit ->
                visit.getDateTime().toLocalDateTime().toLocalDate().isEqual(today));
        } else {
            if (fromDate != null) {
                predicate = predicate.and(visit ->
                    !visit.getDateTime().toLocalDateTime().toLocalDate().isBefore(fromDate));
            }
            if (toDate != null) {
                predicate = predicate.and(visit ->
                    !visit.getDateTime().toLocalDateTime().toLocalDate().isAfter(toDate));
            }
        }

        model.updateFilteredVisitList(predicate);

        // Apply limit if provided
        if (limit != null) {
            model.updateSubFilteredVisitList(limit); // assumes sub-listing is after main filtering
            return new CommandResult("Listed " + Math.min(limit, model.getFilteredVisitList().size()) + " visits");
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
