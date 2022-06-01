module com.valkoshkin.observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valkoshkin.observer to javafx.fxml;
    exports com.valkoshkin.observer;
}