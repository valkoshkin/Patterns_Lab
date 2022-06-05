module com.valkoshkin.templatemethod {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valkoshkin.templatemethod to javafx.fxml;
    exports com.valkoshkin.templatemethod;
}