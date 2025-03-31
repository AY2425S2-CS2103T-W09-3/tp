package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Visit
 */
public class DuplicateVisitException extends RuntimeException {
    public DuplicateVisitException() {
        super("Operation would result in duplicate visits");
    }
}
