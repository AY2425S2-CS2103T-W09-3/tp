package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the patient's symptom.
 */
public class Symptom {
    public final String value;

    public Symptom(String value) {
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
                || (other instanceof Symptom
                && value.equals(((Symptom) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
