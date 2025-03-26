package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the Med Logger.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class NRIC {

    public static final String MESSAGE_CONSTRAINTS =
            "NRIC must be valid. ";

    public static final String VALIDATION_REGEX = "^[A-Z]\\d{7}[A-Z]$";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param value A valid NRIC.
     */
    public NRIC(String value) {
        requireNonNull(value);
        checkArgument(isValidNRIC(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNRIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NRIC)) {
            return false;
        }

        NRIC otherNRIC = (NRIC) other;
        return value.equals(otherNRIC.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
