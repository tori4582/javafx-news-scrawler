module com.fxnews {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.logging;
    requires org.jsoup;
    requires java.desktop;
    requires java.se;

    opens com.fxnews to javafx.fxml;
    exports com.fxnews;
    exports com.fxnews.controller;
    opens com.fxnews.controller to javafx.fxml;
}