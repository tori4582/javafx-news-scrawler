package edu.rmit.newsscrawler.test;

import edu.rmit.newsscrawler.common.*;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static edu.rmit.newsscrawler.common.HtmlMapperUtils.parseArticleLink;
import static edu.rmit.newsscrawler.common.XmlMapperUtils.parseArticle;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
public class UtilsTest {

    @Test
    public void test() {

        var result = CrawlerUtils.crawlDocumentAsChrome("https://vnexpress.net/");
        log.info(result.select("article.item-news").select("h3.title-news a").first().attr("href"));

        log.info(
                Integer.toString(
                        result.select("article.item-news").stream()
                                .map(e -> parseArticleLink(e))
                                .collect(Collectors.toList())
                                .size()
                )
        );


//        var item = d.
//
//        var obj = XmlMapperUtils.parseArticle(item);
//
//        log.info(obj.getUrl());

        assertEquals(true, true);
    }

    @Test
    public void mapperTest() {
        RssReferences.getRssMap().values().stream().forEach(System.out::println);
    }

}
