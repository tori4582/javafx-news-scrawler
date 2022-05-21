package com.fxnews.models;

import java.util.List;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoriesString() {
        return String.join(" / ", categories);
    }

    public Long getPostedDuration() {
        return postedDuration;
    }

    public void setPostedDuration(Long postedDuration) {
        this.postedDuration = postedDuration;
    }
}
