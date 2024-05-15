module progettoispw.homechef {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.mail;

    opens progettoispw.homechef to javafx.fxml;
    exports progettoispw.homechef;
    opens progettoispw.homechef.photo to javafx.fxml;
    exports progettoispw.homechef.bean;
    opens progettoispw.homechef.bean to javafx.fxml;
    exports progettoispw.homechef.controller;
    opens progettoispw.homechef.controller to javafx.fxml;
}