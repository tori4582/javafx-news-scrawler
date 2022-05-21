package com.fxnews.repository;

import com.fxnews.common.HtmlMapperUtils;
import com.fxnews.models.NewsProvider;

public class ZingNewsRepository implements Repository {

    private NewsProvider zingProvider;

    public ZingNewsRepository() {
        zingProvider = NewsProvider.builder()
                .name("Zing News")
                .url("https://news.zing.vn/")
                .categories(HtmlMapperUtils.categoriesStringArrayToMap(this.getProviderCategories()))
                .build();
    }

    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Newest (Mới nhất)", "https://zingnews.vn/",
                "Covid (Covid)","https://zingnews.vn/Covid-19-tim-kiem.html?type=1&content_type=0&date=alltime&category=0",
                "Politics (Chính trị)", "https://zingnews.vn/thoi-su.html",
                "Business (Kinh doanh)", "https://zingnews.vn/kinh-doanh-tai-chinh.html",
                "Technology (Công nghệ)", "https://zingnews.vn/cong-nghe.html",
                "Health (Sức khoẻ)", "https://zingnews.vn/suc-khoe.html",
                "Sports (Thể thao)", "https://zingnews.vn/the-thao.html",
                "Entertainment (Giải trí)", "https://zingnews.vn/giai-tri.html",
                "World (Thế giới)", "https://zingnews.vn/the-gioi.html",
                "Others (Chuyên mục khác)","https://zingnews.vn/xuat-ban.html"
        };
    }

    @Override
    public String[] getCategoryNames() {
        return zingProvider.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return zingProvider.getCategories().get(categoryName);
    }

    @Override
    public NewsProvider get() {
        return this.zingProvider;
    }
}
