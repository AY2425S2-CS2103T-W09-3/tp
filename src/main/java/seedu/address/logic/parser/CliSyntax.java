package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_NRIC = new Prefix("i/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_LIMIT = new Prefix("l/");

    /* For visits */
    public static final Prefix PREFIX_SYMPTOM = new Prefix("sym/");
    public static final Prefix PREFIX_DIAGNOSIS = new Prefix("diag/");
    public static final Prefix PREFIX_MEDICATION = new Prefix("med/");
    public static final Prefix PREFIX_FOLLOWUP = new Prefix("f/");
    public static final Prefix PREFIX_FROM = new Prefix("from/");
    public static final Prefix PREFIX_TO = new Prefix("to/");
    public static final Prefix PREFIX_TODAY = new Prefix("today/");
}
