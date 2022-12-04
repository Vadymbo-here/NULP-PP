module rgr1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens rgr1 to javafx.fxml;
    exports rgr1;
}
