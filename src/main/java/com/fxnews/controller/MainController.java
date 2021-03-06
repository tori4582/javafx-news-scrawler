package com.fxnews.controller;

import com.fxnews.common.RssHandler;
import com.fxnews.FXNews;
import com.fxnews.models.ArticleLink;
import com.fxnews.repository.ProviderRepository;
import com.fxnews.repository.Repository;
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

import static com.fxnews.common.CrawlerUtils.getResponseAsChrome;
import static com.fxnews.common.NewsProviderUtils.parseArticleLink;

@Data
@Log
public class MainController implements Initializable {

    private ProviderRepository providerRepository = ProviderRepository.getInstance();
    private ToggleGroup tg = new ToggleGroup();
    List<ToggleButton> pageButtons = new ArrayList<>();

    @FXML
    private ListView<Pane> chem;

    @FXML
    private Label labelAction;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox paginationPane;

    @FXML
    private ComboBox<String> comboProvider;

    @FXML
    private ComboBox<String> comboCategory;

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

        String providerName = (comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        comboCategory.getItems().clear();
        comboCategory.getItems().addAll(repository.getCategoryNames());

        this.pageButtons.forEach(btn -> btn.setDisable(false));
        ((ToggleButton) this.paginationPane.getChildren().get(0)).setSelected(true);

        this.comboCategory.getSelectionModel().select("Newest (M???i nh???t)");

        doneProgress();
    }

    @FXML
    public void onCategorySelection(Event e) {

        openProgress("Fetching headers");

        String providerName = (comboProvider.getValue()).toUpperCase();
        Repository repository = providerRepository.getProvider(providerName);
        String url = repository.getCategoryUrl(comboCategory.getValue());

        Integer page = Integer.parseInt(
                ((ToggleButton) this.pageButtons
                    .stream()
                    .filter(b -> ((ToggleButton) b).isSelected())
                    .collect(Collectors.toList())
                    .get(0)).getText()
        );

        this.chem.getItems().clear();

        List<Pane> panes;

//        log.info("FETCH: " + providerName + " - " + comboCategory.getValue() + " @ " + url);

        if (List.of("TUOITRE", "VNEXPRESS", "THANHNIEN").contains(providerName)) {
            openProgress("Handling RSS documents");
            panes = this.renderArticleLinks(
                    providerName,
                    url,
                    "item",
                    page,
                    RssHandler.getRssArticleLinkHandler(providerName),
                    getDefaultPaneMapper()
            );
            doneProgress();
        } else {
            panes = this.renderArticleLinks(
                    providerName,
                    url,
                    "article",
                    page,
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
                                          int page,
                                          Function<? super Element, ArticleLink> articleLinkMapper,
                                          Function<ArticleLink, Pane> paneMapper) {

//        log.info(url);

        var result = Jsoup.parse(getResponseAsChrome(url).body());

        return result.select(selector)
                .stream()
                .skip((page - 1) * 10)
                .limit(10)
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

                var loader = new FXMLLoader(FXNews.class.getResource("ArticleLinkComponent.fxml"));

                try {
                    Pane p = new Pane((Pane) loader.load());
                    ArticleDetailController c = loader.getController();
                    c.setArticleLink(articleLink);

                    return p;
                } catch (IOException | IllegalArgumentException ex) {
//                    log.severe(ex.getMessage() + "\n" + ex.getStackTrace().toString());
                    return null;
                }
            }
        );
    }

    public void onPageSwitched(Event e, int index) {
        this.chem.getItems().clear();
        this.onCategorySelection(new Event(EventType.ROOT));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        openProgress("Initializating");

        var providers = providerRepository.getProviders();

        comboProvider.getItems().addAll(providers);

        for (int i = 1; i < 6; i++) {
            var b = new ToggleButton(Integer.toString(i));
            b.setToggleGroup(tg);
            b.setPrefHeight(this.paginationPane.getPrefHeight());
            b.setPrefWidth(b.getPrefHeight());
            b.setMinHeight(b.getPrefHeight());
            b.setMinWidth(b.getPrefWidth());
            b.setOnAction((e) -> {
                pageButtons.stream().forEach(btn -> btn.setDisable(false));
                this.onPageSwitched(e, Integer.parseInt(b.getText()));
                b.setDisable(true);
            });
            pageButtons.add(b);
        }

        pageButtons.get(0).setSelected(true);
        pageButtons.get(0).setDisable(true);

        this.paginationPane.getChildren().addAll(pageButtons);

        this.comboProvider.getSelectionModel().select("VNEXPRESS");
        this.onProviderSelection(new Event(EventType.ROOT));
        this.comboCategory.getSelectionModel().select("Newest (M???i nh???t)");
        this.onCategorySelection(new Event(EventType.ROOT));

    }
}