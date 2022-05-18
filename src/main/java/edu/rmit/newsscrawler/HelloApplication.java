package edu.rmit.newsscrawler;

import edu.rmit.newsscrawler.client.ArticleLinkComponent;
import edu.rmit.newsscrawler.common.RssReferences;
import edu.rmit.newsscrawler.common.XmlMapperUtils;
import edu.rmit.newsscrawler.models.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.CrawlerUtils.crawlDocumentAsChrome;
import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.NewsProviderUtils.fetchRssDocument;
import static edu.rmit.newsscrawler.common.RssReferences.getRssMap;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.toNodeStream;

@Log
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}