package Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import org.controlsfx.control.SearchableComboBox;

public class Search {
    private final SearchableComboBox<String> searchableComboBox;
    //original list of items
    private final ObservableList<String> originalItems;
    private final FilteredList<String> filteredItems;
    private final SortedList<String> sortedItems;
    public Search(SearchableComboBox<String> searchableComboBox, ObservableList<String>originalItems){
        this.searchableComboBox = searchableComboBox;
        this.originalItems = FXCollections.observableArrayList(originalItems);
        this.filteredItems = new FilteredList<>(this.originalItems);
        sortedItems = new SortedList<>(this.filteredItems);
        this.searchableComboBox.setItems(this.sortedItems);
        attachFilterListener();

    }

    private void attachFilterListener() {
        TextField editor = searchableComboBox.getEditor();

        // Listen to changes in the text field and update the ComboBox items accordingly
        editor.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                // If the text field is empty, show all original items
                filteredItems.setPredicate(item -> true);
            } else {
                // Filter items based on the entered text
                filteredItems.setPredicate(item -> item.toLowerCase().contains(newValue.toLowerCase()));
            }
        });
    }
    public SearchableComboBox<String> getSearchableComboBox() {
        return searchableComboBox;
    }
}
