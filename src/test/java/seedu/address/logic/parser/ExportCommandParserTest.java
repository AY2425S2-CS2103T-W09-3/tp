package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ExportCommand.CSV_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

public class ExportCommandParserTest {
    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_correctCsvInput_success() {
        ExportCommand expectedCommand = new ExportCommand(CSV_TYPE);
        assertParseSuccess(parser, CSV_TYPE, expectedCommand);
    }

    @Test
    public void parse_inCorrectInput_failure() {
        String userInput = "something " + CSV_TYPE;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE));
    }
}
