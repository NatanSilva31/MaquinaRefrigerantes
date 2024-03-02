module com.joaocarloslima {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.joaocarloslima to javafx.fxml;
    exports com.joaocarloslima;
}
