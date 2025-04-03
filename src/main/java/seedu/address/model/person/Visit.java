package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Visit of a particular Person.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Visit {
    // Visit details: who, when, why, how
    private final Person person;
    private final DateTime dateTime;
    private final Remark remark;
    private final Symptom symptom;
    private final Diagnosis diagnosis;
    private final Medication medication;
    private final FollowUp followUp;

    /**
     * Every parameter must present and be non-null.
     * @param person
     * @param dateTime
     * @param remark
     */
    public Visit(Person person, DateTime dateTime, Remark remark,
                 Symptom symptom, Diagnosis diagnosis,
                 Medication medication, FollowUp followUp) {
        requireAllNonNull(person, remark, symptom, diagnosis, medication, followUp);
        this.person = person;
        this.dateTime = (dateTime != null) ? dateTime : DateTime.now();
        this.remark = remark;
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.followUp = followUp;
    }

    public Person getPerson() {
        return person;
    }

    public Nric getNric() {
        return person.getNric();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Remark getRemark() {
        return remark;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public Medication getMedication() {
        return medication;
    }

    public FollowUp getFollowUp() {
        return followUp;
    }

    public Visit withPerson(Person newPerson) {
        return new Visit(newPerson, dateTime, remark, symptom, diagnosis, medication, followUp);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Visit)) {
            return false;
        }
        Visit o = (Visit) other;
        return person.equals(o.person)
                && dateTime.equals(o.dateTime)
                && remark.equals(o.remark)
                && symptom.equals(o.symptom)
                && diagnosis.equals(o.diagnosis)
                && medication.equals(o.medication)
                && followUp.equals(o.followUp);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("dateTime", dateTime)
                .add("remark", remark)
                .add("symptom", symptom)
                .add("diagnosis", diagnosis)
                .add("medication", medication)
                .add("followUp", followUp)
                .toString();
    }
}
