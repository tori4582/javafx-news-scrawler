package edu.rmit.newsscrawler.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Article {

    private String title;
    private List<String> categories;
    private String htmlContent;
    private String url;
    private String publishedAt;
    private String author;
    private String source;
    private String imageUrl;
    private Long postedDuration;

    public String getCategoriesString() {
        return String.join(" / ", categories);
    }

}
