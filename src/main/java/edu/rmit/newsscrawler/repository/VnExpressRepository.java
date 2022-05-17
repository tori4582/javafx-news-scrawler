package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.models.Category;
import lombok.Data;

import java.util.List;

@Data
public class VnExpressRepository {

    private final String URL = "https://vnexpress.net/";
    private List<Category> categories;



}
