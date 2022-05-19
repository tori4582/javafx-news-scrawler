package edu.rmit.newsscrawler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class NewsCrawler extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NewsCrawler.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("News Crawler");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}