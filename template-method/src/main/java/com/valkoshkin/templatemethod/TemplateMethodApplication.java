package com.valkoshkin.templatemethod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateMethodApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TemplateMethodApplication.class.getResource("template-method-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        stage.setTitle("Template Method pattern app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}