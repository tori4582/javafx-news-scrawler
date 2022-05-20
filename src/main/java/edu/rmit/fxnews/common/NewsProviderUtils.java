package edu.rmit.fxnews.common;

import edu.rmit.fxnews.models.Article;
import edu.rmit.fxnews.models.ArticleLink;
import lombok.extern.java.Log;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static edu.rmit.fxnews.common.HtmlMapperUtils.*;

@Log
public class NewsProviderUtils {

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

    public static Document fetchRssDocument(String url) {

        try (InputStream is = connectXmlInputStream(url)) {

            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

        } catch (ParserConfigurationException | SAXException | IOException e) {

            log.severe("Error while parsing the XML file: " + e.getMessage());

            return null;
        }
    }

    public static InputStream connectXmlInputStream(final String url) throws IOException {
        return new URL(url).openStream();
    }

}
