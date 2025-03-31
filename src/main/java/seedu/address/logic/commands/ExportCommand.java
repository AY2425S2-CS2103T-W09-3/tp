package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS_JSON = "Data export successfully to json file";

    public static final String MESSAGE_SUCCESS_CSV = "Data export successfully to csv file";

    public static final String MESSAGE_USAGE = "export csv/json";

    public static final String CSV_TYPE = "csv";

    public static final String JSON_TYPE = "json";

    private String fileType;

    public ExportCommand(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (!fileType.equals(CSV_TYPE) && !fileType.equals(JSON_TYPE)) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        Path sourcePath = model.getMedLoggerFilePath();
        String defaultName = "exported_data." + fileType.toLowerCase();

        try {
            FileUtil.saveWithDialog(sourcePath, this.fileType, defaultName);
            return new CommandResult(fileType.equals(CSV_TYPE) ? MESSAGE_SUCCESS_CSV : MESSAGE_SUCCESS_JSON);
        } catch (IOException e) {
            throw new CommandException(String.format(e.getMessage()));
        }

    }

}
