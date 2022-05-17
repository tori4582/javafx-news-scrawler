package edu.rmit.newsscrawler.client;

import edu.rmit.newsscrawler.models.ArticleLink;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Data
public class ArticleLinkComponent {

    @FXML
    private Hyperlink articleLink;

    @FXML
    private ImageView thumbnail;

    @FXML
    private Label description;

    public void setArticleLink(ArticleLink articleLink) throws IOException {
        this.articleLink.setText(articleLink.getTitle());
        this.description.setText(articleLink.getDescription());
        this.thumbnail.setImage(new Image(articleLink.getThumbnailUrl()));
    }
}
