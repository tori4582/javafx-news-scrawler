package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.models.NewsProvider;

public interface Repository {

    String[] getProviderCategories();

    String[] getCategoryNames();

    String getCategoryUrl(String categoryName);

    NewsProvider get();
}
