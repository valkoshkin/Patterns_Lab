package com.valkoshkin.templatemethod;

import com.valkoshkin.templatemethod.model.Ball;
import com.valkoshkin.templatemethod.model.Figure;
import com.valkoshkin.templatemethod.model.Square;
import com.valkoshkin.templatemethod.model.Star;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.Random;

public class TemplateMethodController {
    @FXML
    private Pane areaPane;

    @FXML
    protected void onStartButtonClick() {
        Figure figure;
        var random = new Random().nextDouble();
        if (random <= 1.0 / 3.0) {
            figure = new Ball(areaPane);
        } else if (random <= 2.0 / 3.0) {
            figure = new Square(areaPane);
        } else {
            figure = new Star(areaPane);
        }

        areaPane.getChildren().add(figure.getNode());
        new Thread(figure).start();
    }

    @FXML
    public void onExitButtonClick() {
        System.exit(0);
    }
}