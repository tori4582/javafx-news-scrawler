package edu.rmit.newsscrawler.common;

import edu.rmit.newsscrawler.models.ArticleLink;
import org.jsoup.nodes.Element;

import java.util.Optional;

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

    private static final ArticleLink parseNhanDanArticleLink(Element articleElement) { return null;
    }

    private static final ArticleLink parseTuoiTreArticleLink(Element articleElement) {
        return null;
    }

    private static final ArticleLink parseZingArticleLink(Element articleElement) {
        return null;
    }

    private static final ArticleLink parseThanhNienArticleLink(Element articleElement) { return null;
    }

    private static final ArticleLink parseVnExpressArticleLink(Element articleElement) {
        if (!articleElement.nodeName().equals("article")) {
            throw new IllegalArgumentException("Passed element is not an article element");
        }

        Element titleElement = articleElement.select("h3.title-news a").first();

        if (titleElement == null) {
            return null;
        }

        ArticleLink articleLink = new ArticleLink();

        articleLink.setTitle(titleElement.text());
        articleLink.setUrl(titleElement.attr("href"));
        articleLink.setDescription(articleElement.select("p.description a").text());
        articleLink.setProvider("VN_EXPRESS");

        var thumbnail = articleElement.select("picture img").first();

        if (thumbnail != null) {
            articleLink.setThumbnailUrl(thumbnail.attr("src"));
        }

        return articleLink;
    }

    public static final String nullableString(String str) {
        return (str == null) ? "" : str;
    }



}
