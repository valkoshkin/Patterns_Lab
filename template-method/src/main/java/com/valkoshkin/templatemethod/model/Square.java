package com.valkoshkin.templatemethod.model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Square extends Figure {
    public Square(Pane areaPane) {
        super(new Rectangle(24, 24), areaPane);
    }

    @Override
    protected void configureNode() {
        ((Shape) node).setFill(color);
        var random = new Random();
        node.setLayoutX(random.nextInt(15) + 490);
        node.setLayoutY(random.nextInt(15) + 490);
    }
}
