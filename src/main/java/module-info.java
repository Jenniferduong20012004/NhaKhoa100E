module org.example.learn {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.learn to javafx.fxml;
    exports org.example.learn;
}