package edu.rmit.newsscrawler.client;

import edu.rmit.newsscrawler.HelloApplication;
import edu.rmit.newsscrawler.models.Article;
import edu.rmit.newsscrawler.models.ArticleLink;
import edu.rmit.newsscrawler.repository.ProviderRepository;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.IOException;

import static edu.rmit.newsscrawler.common.CrawlerUtils.crawlDocumentAsChrome;
import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticle;

@Data
@Log
public class ArticleLinkComponent {

    private ArticleLink articleLink;

    @FXML
    private Hyperlink articleHyperLink;

    @FXML
    private ImageView thumbnail;

    @FXML
    private Label description;

    public void setArticleLink(ArticleLink articleLink) throws IOException {

        if (articleLink == null) {
            throw new IllegalArgumentException("This article is null so that the component will not be rendered");
        }

        this.articleHyperLink.setText(articleLink.getTitle());
        this.description.setText(articleLink.getDescription());
        try {
            this.thumbnail.setImage(new Image(articleLink.getThumbnailUrl(), true));
        } catch (IllegalArgumentException | NullPointerException e) {
            log.severe("[" + e.getMessage() + "]: " + articleLink.getThumbnailUrl());
        }

        this.articleLink = articleLink;
    }

    @FXML
    public void onLinkClicked(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewsReader.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        NewsReaderController controller = fxmlLoader.getController();

        Stage stage = new Stage();

        if (!articleLink.getUrl().startsWith("http")) {
            var provider = ProviderRepository.getInstance().getProvider(articleLink.getProvider());

            var seperator = (provider.get().getUrl().endsWith("/")) ? "" : "/" ;

            articleLink.setUrl(provider.get().getUrl() + seperator + articleLink.getUrl());
        }

        var result = crawlDocumentAsChrome(articleLink.getUrl());

        log.info("FETCH->HTML: " + articleLink.getUrl() + " : " + result.select("title").text());

        Article article = parseArticle(articleLink.getProvider(), result);

        controller.load(articleLink, article);

        stage.setScene(scene);
        stage.show();
    }
}
