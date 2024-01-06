package main.sdbm_02;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SDBMMainController {

    @FXML
    private Button buttonArticle;

    @FXML
    private Button buttonVendre;
    @FXML
    public void openArticle() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sdbm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Article Scene");
            stage.show();
        } catch (IOException E) {
            showErrorAlert("Error", "Unable to load Article Scene");       }

    }

    @FXML
    public void openVendre() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vendre.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Vendre Statistique Scene");
            stage.show();
        } catch (IOException E) {
            showErrorAlert("Error", "Unable to load Article Scene");       }

    }
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
