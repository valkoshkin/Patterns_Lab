package com.valkoshkin.observer.model.subscriber;

import com.valkoshkin.observer.model.common.FaceEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class EyeSubscriber implements Subscriber {
    private final Shape shape;
    private final FaceEvent shapeEvent;
    private boolean isOpen = true;

    public EyeSubscriber (Shape shape, FaceEvent event) {
        this.shape = shape;
        this.shapeEvent = event;
    }

    @Override
    public void update(FaceEvent event) {
        if (shapeEvent == event) {
            if (isOpen) {
                shape.setScaleY(0.2);
                shape.setFill(Color.GRAY);
                shape.setStroke(Color.GRAY);
                isOpen = false;
            } else {
                shape.setScaleY(1);
                shape.setFill(Color.BLACK);
                shape.setStroke(Color.LIGHTGRAY);
                isOpen = true;
            }
        }
    }
}
