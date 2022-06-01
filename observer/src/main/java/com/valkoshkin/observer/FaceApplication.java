package com.valkoshkin.observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FaceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FaceApplication.class.getResource("face-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        FaceController controller = fxmlLoader.getController();

        stage.setTitle("Observer pattern app");
        stage.setScene(scene);
        stage.setOnShown(e -> controller.onInit());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}