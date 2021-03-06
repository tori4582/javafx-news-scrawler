package com.fxnews.controller;

import com.fxnews.repository.ProviderRepository;
import com.fxnews.FXNews;
import com.fxnews.models.Article;
import com.fxnews.models.ArticleLink;
import com.fxnews.repository.Repository;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.java.Log;

import java.awt.*;
import java.io.IOException;

import static com.fxnews.common.CrawlerUtils.crawlDocumentAsChrome;
import static com.fxnews.common.NewsProviderUtils.parseArticle;

@Data
@Log
public class ArticleDetailController {

    private ArticleLink articleLink;

    @FXML
    private Hyperlink articleHyperLink;

    @FXML
    private Label publishDateTime;

    @FXML
    private ImageView thumbnail;

    @FXML
    private Label description;

    @FXML
    private Label labelMetadata;

    public void setArticleLink(ArticleLink articleLink) throws IOException {

        if (articleLink == null) {
            throw new IllegalArgumentException("This article is null so that the component will not be rendered");
        }

        this.articleHyperLink.setText(articleLink.getTitle());
        this.description.setText(articleLink.getDescription());
        this.labelMetadata.setText(articleLink.getProvider());
        this.publishDateTime.setText(articleLink.getPublishDateTime());
        try {
            this.thumbnail.setImage(new Image(articleLink.getThumbnailUrl(), true));
        } catch (IllegalArgumentException | NullPointerException e) {
//            log.severe("[" + e.getMessage() + "]: " + articleLink.getThumbnailUrl());
        }

        this.articleLink = articleLink;
    }

    @FXML
    public void onLinkClicked(Event e) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(FXNews.class.getResource("NewsReader.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        NewsReaderController controller = fxmlLoader.getController();

        Stage stage = new Stage();

        if (!articleLink.getUrl().startsWith("http")) {
            Repository provider = ProviderRepository.getInstance().getProvider(articleLink.getProvider());
            String seperator = (provider.get().getUrl().endsWith("/")) ? "" : "/" ;

            articleLink.setUrl(provider.get().getUrl() + seperator + articleLink.getUrl());
        }

        var result = crawlDocumentAsChrome(articleLink.getUrl());

//        log.info("FETCH->HTML: " + articleLink.getUrl() + " : " + result.select("title").text());

        Article article = parseArticle(articleLink.getProvider(), result);
        article.setPublishedAt(articleLink.getPublishDateTime());

        controller.load(articleLink, article);

        stage.setScene(scene);
        stage.show();

        if (article.getHtmlContent() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error information");
            a.setHeaderText("This page contains no content");
            a.setContentText("This problem may be occurred when the loaded page is not a " +
                    "readable content or not in valid format of a post.\nPlease try another page.");
            a.setOnShowing(event -> {
                Toolkit.getDefaultToolkit().beep();
                a.getDialogPane().requestFocus();
            });
            a.show();

        }
    }
}
