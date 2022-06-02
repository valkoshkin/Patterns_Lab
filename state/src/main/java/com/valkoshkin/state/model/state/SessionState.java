package com.valkoshkin.state.model.state;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SessionState implements State {
    private final ImageView imageView;
    private final Image image = new Image(getClass().getResource("/com/valkoshkin/state/img/session.png").toExternalForm());

    public SessionState (ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void renderState() {
        this.imageView.setImage(image);
    }
}
