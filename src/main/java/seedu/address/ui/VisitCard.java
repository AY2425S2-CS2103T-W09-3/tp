package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Visit;

/**
 * An UI component that displays information of a {@code Visit}.
 */
public class VisitCard extends UiPart<Region> {

    private static final String FXML = "VisitListCard.fxml";

    public final Visit visit;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label nric;
    @FXML
    private Label time;
    @FXML
    private Label remark;

    /**
     * Creates a {@code VisitCard} with the given {@code Visit} and index to display.
     */
    public VisitCard(Visit visit, int displayedIndex) {
        super(FXML);
        this.visit = visit;

        id.setText(displayedIndex + ". ");
        name.setText(visit.getPerson().getName().fullName);
        nric.setText("NRIC: " + visit.getPerson().getNric().value);
        time.setText("Visit Time: " + visit.getDateTime().toString());
        remark.setText(visit.getRemark().value.isEmpty()
                ? ""
                : "Remark: " + visit.getRemark().value);
    }
}

