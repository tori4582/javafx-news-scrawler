package edu.rmit.newsscrawler.common;


import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CrawlerUtils {

    @SneakyThrows
    public static final Element crawlDocumentAsChrome(String url) {
        return Jsoup.connect(url).userAgent("Chrome").get().body();
    }


}
