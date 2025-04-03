package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents follow-up information (e.g., review in 1 week).
 */
public class FollowUp {
    public final String value;

    public FollowUp(String value) {
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
                || (other instanceof FollowUp
                && value.equals(((FollowUp) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
