package main.sdbm_02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SDBMApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SDBMApplication.class.getResource("mainSDBM.fxml"));
        SDBMController sdbmController = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 1280, 745);
        stage.setTitle("SDBM Societe des Bieres");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}