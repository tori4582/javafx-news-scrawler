package edu.rmit.newsscrawler;

import edu.rmit.newsscrawler.client.ArticleLinkComponent;
import edu.rmit.newsscrawler.common.RssReferences;
import edu.rmit.newsscrawler.models.ArticleLink;
import edu.rmit.newsscrawler.repository.ProviderRepository;
import edu.rmit.newsscrawler.repository.Repository;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.CrawlerUtils.crawlDocumentAsChrome;
import static edu.rmit.newsscrawler.common.CrawlerUtils.getResponseAsChrome;
import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.NewsProviderUtils.fetchRssDocument;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.selectByTag;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.toNodeArrayList;

@Data
@Log
public class HelloController implements Initializable {

    private ProviderRepository providerRepository = ProviderRepository.getInstance();

    @FXML
    private ListView<Pane> chem;

    @FXML
    private ComboBox comboProvider;

    @FXML
    private ComboBox comboCategory;

    @FXML
    public void onProviderSelection(Event e) {
        String providerName = ((String) comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        comboCategory.getItems().clear();
        comboCategory.getItems().addAll(repository.getCategoryNames());
    }

    @FXML
    public void onCategorySelection(Event e) {

        String providerName = ((String) comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        String url = repository.getCategoryUrl((String) comboCategory.getValue());

        if (providerName.equals("TUOITRE")) {
            this.tuoiTreHandler(url);
            return;
        }

        log.info("FETCH: " + providerName + " - " + comboCategory.getValue() + " @ " + url);

        var response = getResponseAsChrome(url);

//        log.info("RESPONSE: " + response.statusCode());
//        log.info(response.body());

        var result = Jsoup.parse(response.body());

        this.chem.getItems().clear();

        var articleLinksPanes = result.select(
                        (providerName.equals("TUOITRE")) ? "li" : "article"
                )
                .stream()
                .map(element -> parseArticleLink(providerName, element))
                .map(articleLink -> {

                    var loader = new FXMLLoader(HelloApplication.class.getResource("ArticleLinkComponent.fxml"));

                    try {
                        Pane p = new Pane((Pane) loader.load());
                        ArticleLinkComponent c = loader.getController();
                        c.setArticleLink(articleLink);

                        return p;
                    } catch (IOException | IllegalArgumentException ex) {
                        log.severe(ex.getMessage() + "\n" + ex.getStackTrace().toString());
                        return null;
                    }
                })
                .filter(pane -> pane != null)
                .limit(10)
                .collect(Collectors.toList());

        this.chem.getItems().addAll(articleLinksPanes);

    }

    private void tuoiTreHandler(String url) {
        var items = toNodeArrayList(fetchRssDocument(url).getElementsByTagName("item"));

        var articleLinksPanes = items.stream()
                .map(item -> {
                    var articleLink = new ArticleLink();
                    articleLink.setTitle(selectByTag(item.getChildNodes(), "title").get(0).getTextContent());

                    var descriptionTag = selectByTag(item.getChildNodes(), "description").get(0).getTextContent();
                    var element = Jsoup.parse(descriptionTag);

                    articleLink.setThumbnailUrl(element.select("img").attr("src"));
                    articleLink.setDescription(element.text());
                    articleLink.setUrl(selectByTag(item.getChildNodes(), "link").get(0).getTextContent());
                    articleLink.setProvider("TUOITRE");

                    return articleLink;
                }).map(articleLink -> {

                    var loader = new FXMLLoader(HelloApplication.class.getResource("ArticleLinkComponent.fxml"));

                    try {
                        Pane p = new Pane((Pane) loader.load());
                        ArticleLinkComponent c = loader.getController();
                        c.setArticleLink(articleLink);

                        return p;
                    } catch (IOException | IllegalArgumentException ex) {
                        log.severe(ex.getMessage() + "\n" + ex.getStackTrace().toString());
                        return null;
                    }
                })
                .limit(10)
                .collect(Collectors.toList());

        this.chem.getItems().addAll(articleLinksPanes);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboProvider.getItems().addAll(providerRepository.getProviders());
    }
}