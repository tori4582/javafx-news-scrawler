package edu.rmit.newsscrawler.controller;

import edu.rmit.newsscrawler.NewsCrawler;
import edu.rmit.newsscrawler.models.ArticleLink;
import edu.rmit.newsscrawler.repository.ProviderRepository;
import edu.rmit.newsscrawler.repository.Repository;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.CrawlerUtils.getResponseAsChrome;
import static edu.rmit.newsscrawler.common.NewsProviderUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.RssHandler.getRssArticleLinkHandler;

@Data
@Log
public class MainController implements Initializable {

    private ProviderRepository providerRepository = ProviderRepository.getInstance();
    private ToggleGroup tg = new ToggleGroup();

    @FXML
    private ListView<Pane> chem;

    @FXML
    private Label labelAction;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox paginationPane;

    @FXML
    private ComboBox comboProvider;

    @FXML
    private ComboBox comboCategory;

    private void openProgress(String message) {
        this.labelAction.setText(message + "...");
        this.progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
    }

    private void closeProgress(String message) {
        this.labelAction.setText(message);
        this.progressBar.setProgress(1);
    }

    private void doneProgress() {
        this.closeProgress(
                this.labelAction.getText()
                        + ((!this.labelAction.getText().endsWith("DONE")) ? " DONE" : "")
        );
    }

    @FXML
    public void onProviderSelection(Event e) {

        openProgress("Fetching categories");

        String providerName = ((String) comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        comboCategory.getItems().clear();
        comboCategory.getItems().addAll(repository.getCategoryNames());

        ((ToggleButton) this.paginationPane.getChildren().get(0)).setSelected(true);

        doneProgress();
    }

    @FXML
    public void onCategorySelection(Event e) {

        openProgress("Fetching headers");

        String providerName = ((String) comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        String url = repository.getCategoryUrl((String) comboCategory.getValue());

        Integer page = Integer.parseInt(
                ((ToggleButton) this.paginationPane
                    .getChildren()
                    .stream()
                    .filter(b -> ((ToggleButton) b).isSelected())
                    .collect(Collectors.toList())
                    .get(0)).getText()
        );

        this.chem.getItems().clear();

        List<Pane> panes;

        log.info("FETCH: " + providerName + " - " + comboCategory.getValue() + " @ " + url);

        if (List.of("TUOITRE", "VNEXPRESS", "THANHNIEN").contains(providerName)) {
            openProgress("Handling RSS documents");
            panes = this.renderArticleLinks(
                    providerName,
                    url,
                    "item",
                    (page - 1) * 10, (page * 10),
                    getRssArticleLinkHandler(providerName),
                    getDefaultPaneMapper()
            );
            doneProgress();
        } else {
            panes = this.renderArticleLinks(
                    providerName,
                    url,
                    "article",
                    (page - 1) * 10, page * 10,
                    getDefaultHtmlArticleLinkMapper(providerName),
                    getDefaultPaneMapper()
            );
        }

        this.chem.getItems().addAll(panes);
        doneProgress();

    }

    private List<Pane> renderArticleLinks(String providerName,
                                          String url,
                                          String selector,
                                          int fromIndex, int toIndex,
                                          Function<? super Element, ArticleLink> articleLinkMapper,
                                          Function<ArticleLink, Pane> paneMapper) {

        log.info(url);

        var result = Jsoup.parse(getResponseAsChrome(url).body());

        return result.select(selector)
                .stream()
                .skip(fromIndex)
                .limit(toIndex)
                .map(articleLinkMapper)
                .map(paneMapper)
                .filter(pane -> pane != null)
                .collect(Collectors.toList());
    }

    private Function<Element, ArticleLink> getDefaultHtmlArticleLinkMapper(String providerName) {
        return (element -> parseArticleLink(providerName, element));
    }

    private Function<ArticleLink, Pane> getDefaultPaneMapper() {
        return (
            articleLink -> {

                var loader = new FXMLLoader(NewsCrawler.class.getResource("ArticleLinkComponent.fxml"));

                try {
                    Pane p = new Pane((Pane) loader.load());
                    ArticleLinkComponent c = loader.getController();
                    c.setArticleLink(articleLink);

                    return p;
                } catch (IOException | IllegalArgumentException ex) {
                    log.severe(ex.getMessage() + "\n" + ex.getStackTrace().toString());
                    return null;
                }
            }
        );
    }

    public void onPageSwitched(Event e, int index) {
        this.onCategorySelection(new Event(EventType.ROOT));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        openProgress("Initializating");

        var providers = providerRepository.getProviders();

        comboProvider.getItems().addAll(providers);

        List<ToggleButton> pageButtons = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            var b = new ToggleButton(Integer.toString(i));
            b.setToggleGroup(tg);
            b.setPrefHeight(this.paginationPane.getPrefHeight());
            b.setPrefWidth(b.getPrefHeight());
            b.setMinHeight(b.getPrefHeight());
            b.setMinWidth(b.getPrefWidth());
            b.setOnAction((e) -> {
                this.onPageSwitched(e, Integer.parseInt(b.getText()));
            });
            pageButtons.add(b);
        }

        pageButtons.get(0).setSelected(true);

        this.paginationPane.getChildren().addAll(pageButtons);

        this.comboProvider.getSelectionModel().select("VNEXPRESS");
        this.onProviderSelection(new Event(EventType.ROOT));
        this.comboCategory.getSelectionModel().select("Trang chủ");
        this.onCategorySelection(new Event(EventType.ROOT));

    }
}