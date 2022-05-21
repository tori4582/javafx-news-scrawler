package com.fxnews.common;


import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class CrawlerUtils {

    @SneakyThrows
    public static final Element crawlDocumentAsChrome(String url) {
        return Jsoup.connect(url)
                .userAgent("Chrome")
                .timeout(10000)
                .get();
    }

    @SneakyThrows
    public static final Connection.Response getResponseAsChrome(String url) {
        return Jsoup.connect(url)
                .userAgent("Chrome")
                .timeout(10000)
                .method(Connection.Method.GET)
                .followRedirects(true)
                .execute();
    }

}
