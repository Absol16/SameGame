module com.example.samegamefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;
    requires junit;

    opens com.example.samegamefx to javafx.fxml;
    opens com.example.samegamefx.view.JavaFX to javafx.fxml;
    opens com.example.samegamefx.controller to javafx.fxml;

    exports com.example.samegamefx.main;
    exports com.example.samegamefx.view.JavaFX;
    exports com.example.samegamefx.controller;
    exports utils;
    opens utils to javafx.fxml;
}