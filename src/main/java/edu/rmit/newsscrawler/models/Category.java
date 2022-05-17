package edu.rmit.newsscrawler.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {

    private String title;
    private List<String> subCategories;

    private List<Article> articles;

}
