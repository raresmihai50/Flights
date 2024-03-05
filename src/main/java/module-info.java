module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.examen to javafx.fxml;
    exports com.example.examen;

    opens com.example.examen.service;
    exports com.example.examen.service;

    opens com.example.examen.domain;
    exports com.example.examen.domain;

    opens com.example.examen.controller;
    exports com.example.examen.controller;

    opens com.example.examen.repository;
    exports com.example.examen.repository;
}