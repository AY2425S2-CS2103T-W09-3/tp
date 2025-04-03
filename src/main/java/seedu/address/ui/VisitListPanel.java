package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Visit;

/**
 * Panel containing the list of visits.
 */
public class VisitListPanel extends UiPart<Region> {
    private static final String FXML = "VisitListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VisitListPanel.class);

    @FXML
    private ListView<Visit> visitListView;

    /**
     * Creates a {@code VisitListPanel} with the given {@code ObservableList}.
     */
    public VisitListPanel(ObservableList<Visit> visitList) {
        super(FXML);
        visitListView.setItems(visitList);
        visitListView.setCellFactory(listView -> new VisitListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Visit} using a {@code VisitCard}.
     */
    class VisitListViewCell extends ListCell<Visit> {
        @Override
        protected void updateItem(Visit visit, boolean empty) {
            super.updateItem(visit, empty);

            if (empty || visit == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VisitCard(visit, getIndex() + 1).getRoot());
            }
        }
    }
}
