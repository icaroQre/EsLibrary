module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.controller to javafx.fxml;
    exports com.controller;
}
