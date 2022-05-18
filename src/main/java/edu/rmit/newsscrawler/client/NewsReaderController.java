package edu.rmit.newsscrawler.client;

import edu.rmit.newsscrawler.models.Article;
import edu.rmit.newsscrawler.models.ArticleLink;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class NewsReaderController {

    @FXML
    private TextField url;

    @FXML
    private Label author;

    @FXML
    private Label category;

    @FXML
    private Label publishedDateTime;

    @FXML
    private WebView htmlContent;

    @FXML
    private Label title;

    public void load(ArticleLink articleLink, Article article) {
        url.setText(articleLink.getUrl());
        title.setText(article.getTitle());
        htmlContent.getEngine().loadContent(article.getHtmlContent());
        author.setText(article.getAuthor());
        category.setText(article.getCategoriesString());
        publishedDateTime.setText(article.getPublishedAt());
    }


}
