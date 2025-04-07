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
        resultDisplay.getStyleClass().removeAll("result-success", "result-error-light", "result-error-dark");
        boolean isError = feedbackToUser.startsWith("Invalid")
                || feedbackToUser.startsWith("Error")
                || feedbackToUser.startsWith("Unknown");
        if (isError) {
            resultDisplay.getStyleClass().add(isDarkMode ? "result-error-dark" : "result-error-light");
        } else {
            resultDisplay.getStyleClass().add("result-success");
        }
    }

    /**
     * Sets the result display to the colour mode.
     *
     * @param isDarkMode true if the application is in dark mode, false otherwise.
     */
    public void handleToggleTheme(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
        setFeedbackToUser(resultDisplay.getText());
        // if (isDarkMode) {
        //     resultDisplay.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white;");
        // } else {
        //     resultDisplay.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        // }
    }
}
