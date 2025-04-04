package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyMedLogger;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the MedLogger.
     *
     * @see seedu.address.model.Model#getMedLogger()
     */
    ReadOnlyMedLogger getMedLogger();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of visits
     */
    ObservableList<Visit> getFilteredVisitList();

    /**
     * Returns an unmodifiable view of the sorted list of visits
     */
    ObservableList<Visit> getSortedVisitList();

    /**
     * Returns the user prefs' Med Logger file path.
     */
    Path getMedLoggerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
