module controle.animal {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires transitive java.sql;
    requires java.desktop;
    opens controle.animal.controller to javafx.fxml;
    exports controle.animal.controller;
    opens controle.animal.model to javafx.fxml;
    exports controle.animal.model;
    opens controle.animal.main to javafx.fxml;
    exports controle.animal.main;
    opens controle.animal.dao to javafx.fxml;
    exports controle.animal.dao;
}
