package com.fxnews.models;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class NewsProvider {
    private final String name;
    private final String url;

    private Map<String, String> categories;
}
