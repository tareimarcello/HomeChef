module com.example.homechef {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homechef to javafx.fxml;
    exports com.example.homechef;
}