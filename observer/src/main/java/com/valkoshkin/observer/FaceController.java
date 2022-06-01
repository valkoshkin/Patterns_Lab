package com.valkoshkin.observer;

import com.valkoshkin.observer.model.common.FaceEvent;
import com.valkoshkin.observer.model.publisher.PictureEventPublisher;
import com.valkoshkin.observer.model.subscriber.EyeSubscriber;
import com.valkoshkin.observer.model.subscriber.LipsSubscriber;
import com.valkoshkin.observer.model.subscriber.NoseSubscriber;
import javafx.fxml.FXML;
import javafx.scene.shape.Shape;

public class FaceController {
    @FXML
    private Shape leftEye;

    @FXML
    private Shape rightEye;

    @FXML
    private Shape nose;

    @FXML
    private Shape lips;

    private final PictureEventPublisher picturePublisher = new PictureEventPublisher();

    public void onInit() {
        picturePublisher.subscribe(new EyeSubscriber(leftEye, FaceEvent.LEFT_EYE));
        picturePublisher.subscribe(new EyeSubscriber(rightEye, FaceEvent.RIGHT_EYE));
        picturePublisher.subscribe(new NoseSubscriber(nose));
        picturePublisher.subscribe(new LipsSubscriber(lips));
    }

    @FXML
    protected void onLeftEyeClick() {
        picturePublisher.notifyAllSubscribers(FaceEvent.LEFT_EYE);
    }

    @FXML
    protected void onRightEyeClick() {
        picturePublisher.notifyAllSubscribers(FaceEvent.RIGHT_EYE);
    }

    @FXML
    protected void onNoseClick() {
        picturePublisher.notifyAllSubscribers(FaceEvent.NOSE);
    }

    @FXML
    protected void onLipsClick() {
        picturePublisher.notifyAllSubscribers(FaceEvent.LIPS);
    }
}