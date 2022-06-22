module com.example.email {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.email to javafx.fxml;
    exports com.email;
}