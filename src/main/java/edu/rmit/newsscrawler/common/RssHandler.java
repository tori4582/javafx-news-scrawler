package edu.rmit.newsscrawler.common;

import edu.rmit.newsscrawler.models.Article;
import edu.rmit.newsscrawler.models.ArticleLink;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.function.Function;

import static edu.rmit.newsscrawler.common.DateTimeUtils.parseWithFormatMapper;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.removeCData;

@Log
public class RssHandler {

    public static final Function<Element, ArticleLink> getRssArticleLinkHandler(String providerName) {
        return (element -> parseRssArticleLink(providerName, element));
    }

    private static final ArticleLink parseRssArticleLink(String providerName, Element element) {

        switch (providerName) {
            case "THANHNIEN":
                return parseVnExpressArticleLink(providerName, element);
            case "TUOITRE":
                return parseVnExpressArticleLink(providerName, element);
            case "VNEXPRESS":
                return parseVnExpressArticleLink(providerName, element);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + providerName);
        }
    }

    private static final ArticleLink parseVnExpressArticleLink(String providerName, Element element) {
        ArticleLink articleLink = new ArticleLink();

        articleLink.setTitle(removeCData(element.getElementsByTag("title").first().text()));

        var descriptionTag = removeCData(element.select("description").first().text());

        var descriptionElement = Jsoup.parse(descriptionTag);

        articleLink.setThumbnailUrl(removeCData(descriptionElement.select("img").attr("src")));
        articleLink.setDescription(removeCData(descriptionElement.text()));

        var articleUrl = descriptionElement.select("a").first();

        String url = (articleUrl == null)
                    ? element.select("link").text()
                    : articleUrl.attr("href");

        articleLink.setUrl(url);
        articleLink.setPublishDateTime(
                parseWithFormatMapper(
                        providerName,
                        removeCData(element.getElementsByTag("pubdate").first().text())
                )
        );
        articleLink.setProvider(providerName);

        return articleLink;
    }
//
//    private static final ArticleLink parseNhanDanArticleLink(Element element) {
//
//    }

}
