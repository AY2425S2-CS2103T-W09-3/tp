package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a DateTime in the system.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be in the format yyyy-mm-dd HH:mm and valid";
    public static final String MESSAGE_CONSTRAINTS_DATE_ONLY = "Dates should be in the format yyyy-mm-dd and valid";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);
    //uuuu must be used with strict style which is needed to detect all invalid dates

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param date A valid date in yyyy-mm-dd HH:mm format.
     */
    public DateTime(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if a given string is a valid date in yyyy-mm-dd HH:mm format.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDateTime.parse(date, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date in yyyy-mm-dd format only.
     * @param date  A valid date in yyyy-mm-dd format.
     * @return true if the date is valid.
     */
    public static boolean isValidDateOnly(String date) {
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(date, dateOnlyFormatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date in yyyy-mm-dd HH:mm format.
     */
    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.parse(value, FORMATTER);
    }

    /**
     * Returns the current date and time in the format yyyy-mm-dd HH:mm.
     * @return The current date and time
     */
    public static DateTime now() {
        return new DateTime(LocalDateTime.now().format(FORMATTER));
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
        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime otherDateTime = (DateTime) other;
        return value.equals(otherDateTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
