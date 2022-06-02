package com.valkoshkin.state.model.state;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SemesterState implements State {
    private final ImageView imageView;
    private final Image image = new Image(getClass().getResource("/com/valkoshkin/state/img/semester.png").toExternalForm());

    public SemesterState (ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void renderState() {
        imageView.setImage(image);
    }
}
