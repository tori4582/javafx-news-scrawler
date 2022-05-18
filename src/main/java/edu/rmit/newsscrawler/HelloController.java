package edu.rmit.newsscrawler;

import edu.rmit.newsscrawler.client.ArticleLinkComponent;
import edu.rmit.newsscrawler.common.RssReferences;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.CrawlerUtils.crawlDocumentAsChrome;
import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;

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

        log.info("FETCH: " + providerName + " - " + comboCategory.getValue());

        var result = crawlDocumentAsChrome(repository.getCategoryUrl((String) comboCategory.getValue()));

        this.chem.getItems().clear();

        var articleLinksPanes = result.select("article")
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboProvider.getItems().addAll(providerRepository.getProviders());
    }
}