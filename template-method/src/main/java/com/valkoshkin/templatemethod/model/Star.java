package com.valkoshkin.templatemethod.model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Star extends Figure {
    public Star(Pane areaPane) {
        super(new Polygon(
                20, 40,
                24, 24,
                40, 24,
                30, 20,
                36, 0,
                20, 16,
                4, 0,
                16, 20,
                0, 24,
                16, 24
        ), areaPane);
    }

    @Override
    protected void configureNode() {
        ((Shape) node).setFill(color);
        var random = new Random();
        node.setLayoutX(random.nextInt(10) + 500);
        node.setLayoutY(random.nextInt(10) + 500);
    }
}
