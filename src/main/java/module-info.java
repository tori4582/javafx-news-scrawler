module edu.rmit.newsscrawler {
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


    opens edu.rmit.newsscrawler to javafx.fxml;
    exports edu.rmit.newsscrawler;
    exports edu.rmit.newsscrawler.client;
    opens edu.rmit.newsscrawler.client to javafx.fxml;
}