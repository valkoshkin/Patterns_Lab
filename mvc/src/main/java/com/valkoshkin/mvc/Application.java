package com.valkoshkin.mvc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        Controller controller = fxmlLoader.getController();

        stage.setResizable(false);
        stage.setTitle("MVC pattern app");
        stage.setScene(scene);
        stage.setOnShown(e -> controller.onInit());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}