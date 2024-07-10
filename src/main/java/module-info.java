module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.sql;
    requires java.desktop;

    opens com.example to javafx.fxml;
    opens com.example.interactor to javafx.fxml;
    opens com.example.model to javafx.base;
    exports com.example;
    exports com.example.interactor;
}