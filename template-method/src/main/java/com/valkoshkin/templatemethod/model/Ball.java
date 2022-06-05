package com.valkoshkin.templatemethod.model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Ball extends Figure {
    public Ball(Pane areaPane) {
        super(new Circle(12), areaPane);
    }

    @Override
    protected void configureNode() {
        ((Shape) node).setFill(color);
        var random = new Random();
        node.setLayoutX(random.nextInt(10) + 510);
        node.setLayoutY(random.nextInt(10) + 510);
    }
}
