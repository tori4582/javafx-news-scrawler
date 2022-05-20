package edu.rmit.fxnews.repository;

import edu.rmit.fxnews.models.NewsProvider;

public interface Repository {

    String[] getProviderCategories();

    String[] getCategoryNames();

    String getCategoryUrl(String categoryName);

    NewsProvider get();
}
