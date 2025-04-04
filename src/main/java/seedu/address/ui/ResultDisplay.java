package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private boolean isDarkMode = true;

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
        resultDisplay.setStyle(
            feedbackToUser.startsWith("Invalid") || feedbackToUser.startsWith("Error")
                    || feedbackToUser.startsWith("Unknown")
                    ? (this.isDarkMode ? "-fx-text-fill: white;" : "-fx-text-fill: black;")
                    : "-fx-text-fill: green;"
        );
    }

    /**
     * Sets the result display to the colour mode.
     *
     * @param isDarkMode true if the application is in dark mode, false otherwise.
     */
    public void handleToggleTheme(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
        // if (isDarkMode) {
        //     resultDisplay.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white;");
        // } else {
        //     resultDisplay.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        // }
    }
}
