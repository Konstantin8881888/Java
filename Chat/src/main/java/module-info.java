module com.example.client_chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires javafx.graphics;
    requires javafx.base;

    opens com.example.client_chat to javafx.fxml;
    exports com.example.client_chat;
}