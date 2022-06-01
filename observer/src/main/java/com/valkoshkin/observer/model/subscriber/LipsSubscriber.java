package com.valkoshkin.observer.model.subscriber;

import com.valkoshkin.observer.model.common.FaceEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;

public class LipsSubscriber implements Subscriber {
    private final QuadCurve shape;
    private final int DEFAULT_CONTROL_Y = 400;

    public LipsSubscriber(Shape shape) {
        this.shape = (QuadCurve) shape;
    }

    @Override
    public void update(FaceEvent event) {
        if (event == FaceEvent.LIPS) {
            if (shape.getControlY() == DEFAULT_CONTROL_Y) {
                shape.setControlY(470);
                shape.setStartY(390);
                shape.setEndY(390);
                shape.setStrokeWidth(1);
                shape.setStroke(Color.LIGHTGRAY);
            } else {
                shape.setControlY(400);
                shape.setStrokeWidth(10);
                shape.setStartY(400);
                shape.setEndY(400);
                shape.setStroke(Color.GRAY);
            }
        }
    }
}
