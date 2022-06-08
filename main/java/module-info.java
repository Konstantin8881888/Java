module com.example.lesson24 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.lesson24 to javafx.fxml;
    exports com.example.lesson24;
}