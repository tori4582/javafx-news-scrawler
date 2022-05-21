package com.fxnews.repository;

import com.fxnews.models.NewsProvider;

public interface Repository {

    String[] getProviderCategories();

    String[] getCategoryNames();

    String getCategoryUrl(String categoryName);

    NewsProvider get();
}
