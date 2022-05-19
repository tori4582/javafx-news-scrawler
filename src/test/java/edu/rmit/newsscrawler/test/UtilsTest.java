package edu.rmit.newsscrawler.test;

import edu.rmit.newsscrawler.common.*;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.NewsProviderUtils.fetchRssDocument;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.parseArticle;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
public class UtilsTest {

    @Test
    public void test() {

        String url = "https://tuoitre.vn/rss/the-gioi.rss";

        var l = fetchRssDocument(url).getElementsByTagName("item");

        for (int i = 0; i < l.getLength(); i++) {
            var item = l.item(i);
            var link = item.getChildNodes().item(1);
            log.severe(link.getNodeName() + ": " + link.getTextContent());
        }


        assertEquals(true, true);
    }

    @Test
    public void mapperTest() {
        RssReferences.getRssMap().values().stream().forEach(System.out::println);
    }

}
