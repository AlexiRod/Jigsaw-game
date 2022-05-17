module ru.hse.hw5.homework5 {
    requires javafx.controls;
    requires javafx.fxml;


    exports ru.hse.hw5.homework5.app.controllers;
    opens ru.hse.hw5.homework5.app.controllers to javafx.fxml;
    exports ru.hse.hw5.homework5;
    opens ru.hse.hw5.homework5 to javafx.fxml;
}