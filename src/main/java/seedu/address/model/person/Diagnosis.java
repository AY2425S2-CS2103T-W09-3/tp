package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents the doctor's diagnosis for a visit.
 */
public class Diagnosis {
    public final String value;

    /**
     * Constructs a {@code Diagnosis}.
     *
     * @param value Some diagnosis.
     */
    public Diagnosis(String value) {
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
                || (other instanceof Diagnosis
                && value.equals(((Diagnosis) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
