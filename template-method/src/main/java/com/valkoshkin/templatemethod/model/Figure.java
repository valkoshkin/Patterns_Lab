package com.valkoshkin.templatemethod.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public abstract class Figure implements Runnable {
    protected Node node;
    protected Pane areaPane;

    protected double height;
    protected double width;
    protected double speedX;
    protected double speedY;

    protected Color color;

    public Figure(Node node, Pane areaPane) {
        this.node = node;
        this.areaPane = areaPane;

        height = areaPane.getHeight();
        width = areaPane.getWidth();

        Random random = new Random();
        speedX = random.nextDouble() - 2;
        speedY = random.nextDouble() - 2;
        color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    @Override
    public final void run() {
        configureNode();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> onFrameFinished()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    protected abstract void configureNode();

    public Node getNode() {
        return node;
    }

    protected void onFrameFinished() {
        handleSpeed();
        moveNode();
    }

    protected void handleSpeed() {
        var bounds = node.getBoundsInParent();

        if (bounds.getMinX() <= 0 || bounds.getMaxX() >= width) {
            speedX = -speedX;
        } else if (bounds.getMinY() <= 0 || bounds.getMaxY() >= height) {
            speedY = -speedY;
        }
    }

    protected void moveNode() {
        node.setLayoutX(node.getLayoutX() + speedX);
        node.setLayoutY(node.getLayoutY() + speedY);
    }
}
