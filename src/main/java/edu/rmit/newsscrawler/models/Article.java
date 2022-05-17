package edu.rmit.newsscrawler.models;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class Article {

    private String title;
    private String content;
    private String url;
    private LocalDate publishedAt;
    private String author;
    private String source;
    private String imageUrl;
    private Long postedDuration;

}
