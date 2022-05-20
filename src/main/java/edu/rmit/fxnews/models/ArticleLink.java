package edu.rmit.fxnews.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleLink {
    private String url;
    private String title;
    private String description;
    private String provider;
    private String thumbnailUrl;
    private String publishDateTime;
}
