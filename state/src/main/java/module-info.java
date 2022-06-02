module com.valkoshkin.state {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valkoshkin.state to javafx.fxml;
    exports com.valkoshkin.state;
}