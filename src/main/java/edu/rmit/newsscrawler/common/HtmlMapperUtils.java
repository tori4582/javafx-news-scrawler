package edu.rmit.newsscrawler.common;

import edu.rmit.newsscrawler.models.Article;
import edu.rmit.newsscrawler.models.ArticleLink;
import lombok.extern.java.Log;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
public class HtmlMapperUtils {

    public static final ArticleLink parseArticleLink(String newsProvider, Element articleElement) {

        switch (newsProvider) {
            case "VNEXPRESS":
                return parseVnExpressArticleLink(articleElement);
            case "THANHNIEN":
                return parseThanhNienArticleLink(articleElement);
            case "ZING":
                return parseZingArticleLink(articleElement);
            case "TUOITRE":
                return parseTuoiTreArticleLink(articleElement);
            case "NHANDAN":
                return parseNhanDanArticleLink(articleElement);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + newsProvider);
        }
    }

    public static final Article parseArticle(String newsProvider, Element bodyElement) {

        switch (newsProvider) {
            case "VNEXPRESS":
                return parseVnExpressArticle(bodyElement);
            case "THANHNIEN":
                return parseThanhNienArticle(bodyElement);
            case "ZING":
                return parseZingArticle(bodyElement);
            case "TUOITRE":
                return parseTuoiTreArticle(bodyElement);
            case "NHANDAN":
                return parseNhanDanArticle(bodyElement);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + newsProvider);
        }
    }

    private static final ArticleLink parseNhanDanArticleLink(Element articleElement) {
        var link =  parseQuerier(
                articleElement,
                "div.box-title>a",
                "div.box-desc>p",
                "div.box-img>a>img",
                "div.box-meta-small"
        );

        if (link != null) {
            link.setThumbnailUrl(articleElement.select("div.box-img img").attr("data-src"));
            link.setProvider("NHANDAN");
        }

        return link;
    }

    private static final ArticleLink parseTuoiTreArticleLink(Element articleElement) {
        var link =  parseQuerier(
                articleElement,
                "h3 a",
                "p.sapo",
                "li.news-item a img",
                ""
        );

        if (link != null) {

            log.info(articleElement.select("li.news-item a img").html());

            link.setThumbnailUrl(articleElement.select("li.news-item a img").attr("src"));
            link.setProvider("TUOITRE");
        }

        return link;
    }

    private static final ArticleLink parseZingArticleLink(Element articleElement) {

        var link =  parseQuerier(
                articleElement,
                ".article-title a",
                "p.article-summary",
                ".article-thumbnail img",
                "p.article-meta span.friendly-time"
        );

        if (link != null) {
            link.setProvider("ZING");
        }

        return link;
    }

    private static final ArticleLink parseThanhNienArticleLink(Element articleElement) {
        ArticleLink link = parseQuerier(
                articleElement,
                "h2 a",
                "div.summary p",
                "article>a>img",
                "div.meta>span.time"
        );

        if (link != null) {
            link.setProvider("THANHNIEN");
        }

        return link;
    }

    private static final ArticleLink parseVnExpressArticleLink(Element articleElement) {

        ArticleLink link = parseQuerier(
                articleElement,
                ".title-news a",
                "p.description a",
                "article>a>img",
                ""
        );

        if (link != null) {
            link.setThumbnailUrl(articleElement.select("article>a>img").attr("data-src"));
            link.setProvider("VNEXPRESS");
        }

        return link;
    }

    public static final String nullableString(String str) {
        return (str == null) ? "" : str;
    }


    public static final Map<String, String> categoriesStringArrayToMap(String[] categories) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < categories.length; i++) {
            if (i % 2 == 0) {
                map.put(categories[i], categories[i + 1]);
            }
        }

        return map;
    }

    public static final ArticleLink parseQuerier(Element articleElement, String titleQuery, String descQuery, String thumbnailQuery, String timeQuery) {

        Element titleElement = articleElement.select(titleQuery).first();

        if (titleElement == null) {
            return null;
        }

        ArticleLink articleLink = new ArticleLink();

        articleLink.setTitle(titleElement.text());
        articleLink.setUrl(titleElement.attr("href"));
        articleLink.setDescription(articleElement.select(descQuery).text());
        articleLink.setPublishDateTime(articleElement.select(timeQuery).text());

        var thumbnail = articleElement.select(thumbnailQuery).first();

        if (thumbnail != null) {
            articleLink.setThumbnailUrl(
                    (thumbnail.attr("src").startsWith("data"))
                            ? thumbnail.attr("data-src")
                            : thumbnail.attr("src")
            );
        }

        return articleLink;
    }



    private static final Article parseNhanDanArticle(Element bodyElement) {
        return Article.builder()
                .title(bodyElement.select("h1").text())
                .publishedAt(bodyElement.select("div.box-date").text())
                .author(bodyElement.select("div.box-author").text())
                .htmlContent(forceLoadLazyData(bodyElement.select("div.box-content-detail").first()))
                .categories(
                        bodyElement.select("p.the-article-category a")
                                .stream()
                                .map(e -> e.text())
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static final Article parseTuoiTreArticle(Element bodyElement) {

        return Article.builder()
                .title(bodyElement.select("h1").text())
                .publishedAt(bodyElement.select("div.date-time").text())
                .author(bodyElement.select("div.author").text())
                .htmlContent(bodyElement.select("div.content.fck").first().html())
                .categories(
                        bodyElement.select("p.the-article-category a")
                                .stream()
                                .map(e -> e.text())
                                .collect(Collectors.toList())
                )
                .build();

    }

    private static final Article parseZingArticle(Element bodyElement) {
        return Article.builder()
                .title(bodyElement.select("h1").text())
                .publishedAt(
                        bodyElement.select("li.the-article-publish").text()
                                + " (" + bodyElement.select("li.the-article-friendly-time").text() + ")"
                ).author(bodyElement.select("li.the-article-author").text())
                .htmlContent(forceLoadLazyData(bodyElement.select("section.main").first()))
                .categories(
                        bodyElement.select("p.the-article-category a")
                                .stream()
                                .map(e -> e.text())
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static final Article parseThanhNienArticle(Element bodyElement) {
        return Article.builder()
                .title(bodyElement.select("h1").text())
                .publishedAt(bodyElement.select("div.meta time").text())
                .author(bodyElement.select("h4>a.cms-author").text())
                .htmlContent(forceLoadLazyData(bodyElement.select("div#abody").first()))
                .categories(
                        bodyElement.select("div.breadcrumb>a")
                                .stream()
                                .map(e -> e.text())
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static final Article parseVnExpressArticle(Element bodyElement) {

        return Article.builder()
                .title(bodyElement.select("h1").text())
                .publishedAt(bodyElement.select("span.date").text())
                .author(bodyElement.select("p.normal strong").text())
                .htmlContent(forceLoadLazyData(bodyElement.select("article.fck_detail").first()))
                .categories(
                        bodyElement.select("ul.breadcrumb li a")
                                .stream()
                                .map(e -> e.text())
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static final String forceLoadLazyData(Element element) {

        List<String> rawImages = element.select("img")
                .stream()
                .map(e -> e.html())
                .collect(Collectors.toList());
        List<String> fixedImages = element.select("img")
                .stream()
                .map(
                    imgElement -> imgElement.attr(
                        "src",
                        (imgElement.attr("src").startsWith("data"))
                                ? imgElement.attr("data-src")
                                : imgElement.attr("src")
                    )
                ).map(e -> e.html()).collect(Collectors.toList());

        String html = element.html();
        for (int i = 0; i < rawImages.size(); i++) {
            html = html.replace(rawImages.get(i), fixedImages.get(i));
        }

        return html;

    }
}

