package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalMedLogger;

import org.junit.jupiter.api.Test;

import seedu.address.model.MedLogger;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMedLogger_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMedLogger_success() {
        Model model = new ModelManager(getTypicalMedLogger(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMedLogger(), new UserPrefs());
        expectedModel.setMedLogger(new MedLogger());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
