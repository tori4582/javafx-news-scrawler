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
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.CrawlerUtils.crawlDocumentAsChrome;
import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.NewsProviderUtils.fetchRssDocument;
import static edu.rmit.newsscrawler.common.RssReferences.getRssMap;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.toNodeStream;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setOnShowing(event -> {
            HelloController controller = fxmlLoader.getController();
            ListView<AnchorPane> t = controller.getTopicList();

            var result = crawlDocumentAsChrome("https://vnexpress.net/");

            var articleLinks = result.select("article.item-news")
                    .stream()
                    .map(e -> parseArticleLink("VNEXPRESS", e))
                    .collect(Collectors.toList());

            try {
                var loader = new FXMLLoader(HelloApplication.class.getResource("ArticleLinkComponent.fxml"));
                ArticleLinkComponent c = (ArticleLinkComponent) loader.getController();

                AnchorPane p = new AnchorPane((AnchorPane) loader.load());

                AnchorPane p1 = new AnchorPane((AnchorPane) loader.load());
                c.setArticleLink(articleLinks.get(1));
                t.getItems().add(p);

                c.setArticleLink(articleLinks.get(2));
                t.getItems().add(p1);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}