module com.mycompany.atm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; // Add near top
    requires java.base;
    requires java.sql; // Add near top

    opens com.mycompany.atm to javafx.fxml, com.google.gson;
    exports com.mycompany.atm;
}
