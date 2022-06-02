package com.valkoshkin.state;

import com.valkoshkin.state.model.Context;
import com.valkoshkin.state.model.state.SemesterState;
import com.valkoshkin.state.model.state.SessionState;
import com.valkoshkin.state.model.state.VacationState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StateController {
    @FXML
    protected ImageView imageView;

    private final Context context = new Context();

    @FXML
    protected void onSemesterButtonClick() {
        context.setState(new SemesterState(imageView));
        context.render();
    }

    @FXML
    protected void onSessionButtonClick() {
        context.setState(new SessionState(imageView));
        context.render();
    }

    @FXML
    protected void onVacationButtonClick() {
        context.setState(new VacationState(imageView));
        context.render();
    }
}