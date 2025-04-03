package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents medication prescribed to the patient.
 */
public class Medication {
    public final String value;

    /**
     * Constructs a {@code Medication}.
     *
     * @param value Some medication.
     */
    public Medication(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Medication
                && value.equals(((Medication) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
