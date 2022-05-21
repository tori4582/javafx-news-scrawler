package com.fxnews.common;

import com.fxnews.models.ArticleLink;
import com.fxnews.models.Article;
import lombok.extern.java.Log;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Log
public class NewsProviderUtils {

    public static final ArticleLink parseArticleLink(String newsProvider, Element articleElement) {

        switch (newsProvider) {
            case "VNEXPRESS":
                return HtmlMapperUtils.parseVnExpressArticleLink(articleElement);
            case "THANHNIEN":
                return HtmlMapperUtils.parseThanhNienArticleLink(articleElement);
            case "ZING":
                return HtmlMapperUtils.parseZingArticleLink(articleElement);
            case "TUOITRE":
                return HtmlMapperUtils.parseTuoiTreArticleLink(articleElement);
            case "NHANDAN":
                return HtmlMapperUtils.parseNhanDanArticleLink(articleElement);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + newsProvider);
        }
    }

    public static final Article parseArticle(String newsProvider, Element bodyElement) {

        switch (newsProvider) {
            case "VNEXPRESS":
                return HtmlMapperUtils.parseVnExpressArticle(bodyElement);
            case "THANHNIEN":
                return HtmlMapperUtils.parseThanhNienArticle(bodyElement);
            case "ZING":
                return HtmlMapperUtils.parseZingArticle(bodyElement);
            case "TUOITRE":
                return HtmlMapperUtils.parseTuoiTreArticle(bodyElement);
            case "NHANDAN":
                return HtmlMapperUtils.parseNhanDanArticle(bodyElement);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + newsProvider);
        }
    }

    public static Document fetchRssDocument(String url) {

        try (InputStream is = connectXmlInputStream(url)) {

            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

        } catch (ParserConfigurationException | SAXException | IOException e) {

//            log.severe("Error while parsing the XML file: " + e.getMessage());

            return null;
        }
    }

    public static InputStream connectXmlInputStream(final String url) throws IOException {
        return new URL(url).openStream();
    }

}
