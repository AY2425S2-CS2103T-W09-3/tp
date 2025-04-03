package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.FollowUp;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Symptom;
import seedu.address.model.person.Visit;

/**
 * Jackson-friendly version of {@link Visit}.
 */
class JsonAdaptedVisit {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Visit's %s field is missing!";

    private final String nric;
    private final String dateTime;
    private final String remark;

    private final String symptom;
    private final String diagnosis;
    private final String medication;
    private final String followUp;

    /**
     * Constructs a {@code JsonAdaptedVisit} with the given visit details.
     */
    @JsonCreator
    public JsonAdaptedVisit(@JsonProperty("NRIC") String nric,
                            @JsonProperty("dateTime") String dateTime,
                            @JsonProperty("remark") String remark,
                            @JsonProperty("symptom") String symptom,
                            @JsonProperty("diagnosis") String diagnosis,
                            @JsonProperty("medication") String medication,
                            @JsonProperty("followUp") String followUp) {
        this.nric = nric;
        this.dateTime = dateTime;
        this.remark = remark;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.followUp = followUp;
    }

    /**
     * Converts a given {@code Visit} into this class for Jackson use.
     */
    public JsonAdaptedVisit(Visit source) {
        nric = source.getNric().value;
        dateTime = source.getDateTime().value;
        remark = source.getRemark().value;
        symptom = source.getSymptom().value;
        diagnosis = source.getDiagnosis().value;
        medication = source.getMedication().value;
        followUp = source.getFollowUp().value;
    }

    public Nric getNric() throws IllegalValueException {
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(nric);
    }

    /**
     * Converts this Jackson-friendly adapted visit object into the model's {@code Visit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted visit.
     */
    public Visit toModelType(Person person) throws IllegalValueException {
        // Handle dateTime
        final DateTime modelDateTime;
        if (dateTime == null) {
            modelDateTime = DateTime.now();
        } else if (!DateTime.isValidDate(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        } else {
            modelDateTime = new DateTime(dateTime);
        }

        // Handle remark
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        // Handle optional fields with fallback
        final Symptom modelSymptom = new Symptom(symptom != null ? symptom : "N/A");
        final Diagnosis modelDiagnosis = new Diagnosis(diagnosis != null ? diagnosis : "N/A");
        final Medication modelMedication = new Medication(medication != null ? medication : "N/A");
        final FollowUp modelFollowUp = new FollowUp(followUp != null ? followUp : "N/A");

        return new Visit(person, modelDateTime, modelRemark,
                modelSymptom, modelDiagnosis, modelMedication, modelFollowUp);
    }
}
