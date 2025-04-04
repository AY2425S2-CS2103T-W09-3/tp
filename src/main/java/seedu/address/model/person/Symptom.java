package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the patient's symptom.
 */
public class Symptom {
    public final String value;

    /**
     * Constructs a {@code Symptom}.
     * @param value some symptom.
     */
    public Symptom(String value) {
        requireNonNull(value);
        this.value = value;
    }

    public String toLowerCase() {
        return value.toLowerCase();
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
