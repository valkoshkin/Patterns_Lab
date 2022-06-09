module com.valkoshkin.mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valkoshkin.mvc to javafx.fxml;
    exports com.valkoshkin.mvc;
    exports com.valkoshkin.mvc.model;
}