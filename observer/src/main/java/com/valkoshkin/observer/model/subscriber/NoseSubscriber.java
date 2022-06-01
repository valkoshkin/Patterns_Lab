package com.valkoshkin.observer.model.subscriber;

import com.valkoshkin.observer.model.common.FaceEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class NoseSubscriber implements Subscriber {
    private final Shape shape;

    public NoseSubscriber (Shape shape) {
        this.shape = shape;
    }

    @Override
    public void update(FaceEvent event) {
        if (event == FaceEvent.NOSE) {
            shape.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
        }
    }
}
