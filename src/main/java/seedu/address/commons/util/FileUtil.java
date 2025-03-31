package seedu.address.commons.util;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.stage.FileChooser;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Writes and reads files
 */
public class FileUtil {

    private static final String CHARSET = "UTF-8";

    public static boolean isFileExists(Path file) {
        return Files.exists(file) && Files.isRegularFile(file);
    }

    /**
     * Returns true if {@code path} can be converted into a {@code Path} via {@link Paths#get(String)},
     * otherwise returns false.
     * @param path A string representing the file path. Cannot be null.
     */
    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException ipe) {
            return false;
        }
        return true;
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     * @throws IOException if the file or directory cannot be created.
     */
    public static void createIfMissing(Path file) throws IOException {
        if (!isFileExists(file)) {
            createFile(file);
        }
    }

    /**
     * Creates a file if it does not exist along with its missing parent directories.
     */
    public static void createFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }

        createParentDirsOfFile(file);

        Files.createFile(file);
    }

    /**
     * Creates parent directories of file if it has a parent directory
     */
    public static void createParentDirsOfFile(Path file) throws IOException {
        Path parentDir = file.getParent();

        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
    }

    /**
     * Assumes file exists
     */
    public static String readFromFile(Path file) throws IOException {
        return new String(Files.readAllBytes(file), CHARSET);
    }

    /**
     * Writes given string to a file.
     * Will create the file if it does not exist yet.
     */
    public static void writeToFile(Path file, String content) throws IOException {
        Files.write(file, content.getBytes(CHARSET));
    }

    public static void saveWithDialog(Path sourcePath, String fileType, String defaultName) throws IOException {
        File selectedFile = FileUtil.promptSaveDialog(fileType, defaultName);
        if (selectedFile == null) {
            throw new IOException("Export cancelled by user.");
        }

        String content = FileUtil.readFromFile(sourcePath);
        if (fileType.equals("json")) {
            FileUtil.writeToFile(selectedFile.toPath(), content);
        } else if (fileType.equals("csv")) {

            List<Map<String, String>> rows;
            try {
                rows = extractJsonObjects(content, "persons");
            } catch (Exception e) {
                throw new IOException("Error when parsing the file");
            }

            if (rows.isEmpty()) {
                throw new IOException("No data to export.");
            }

            // Get keys from first object (assumes all rows have same structure)
            List<String> headers = new ArrayList<>(rows.get(0).keySet());
            StringBuilder csvBuilder = new StringBuilder();

            // Write header
            csvBuilder.append(String.join(",", headers)).append("\n");

            // Write rows
            for (Map<String, String> row : rows) {
                List<String> cells = new ArrayList<>();
                for (String key : headers) {
                    String value = row.getOrDefault(key, "");
                    cells.add(quote(value));
                }
                csvBuilder.append(String.join(",", cells)).append("\n");
            }

            FileUtil.writeToFile(selectedFile.toPath(), csvBuilder.toString());
        } else {
            throw new IOException("Invalid fileType");
        }

    }

    private static File promptSaveDialog(String fileType, String defaultName) {
        // Use JavaFX FileChooser to prompt the user for save location
        FileChooser fileChooser = new FileChooser();

        // Set the appropriate file extension filter
        String s1 = fileType.toUpperCase() + " Files";
        String s2 = "*." + fileType;

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(s1, s2));
        fileChooser.setTitle("Save " + fileType.toUpperCase() + " File");

        // Suggest default file name
        fileChooser.setInitialFileName(defaultName);

        return fileChooser.showSaveDialog(null);
    }

    private static List<Map<String, String>> extractJsonObjects(String json, String arrayKey) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode arrayNode = root.get(arrayKey);

        if (arrayNode == null || !arrayNode.isArray()) {
            return Collections.emptyList();
        }

        List<Map<String, String>> result = new ArrayList<>();

        for (JsonNode node : arrayNode) {
            Map<String, String> map = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();


            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                map.put(entry.getKey(), entry.getValue().toString().replaceAll("^\"|\"$", ""));
            }

            result.add(map);
        }

        return result;
    }

    private static String quote(String value) {
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
