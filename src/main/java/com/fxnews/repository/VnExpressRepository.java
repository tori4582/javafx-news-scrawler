package com.fxnews.repository;

import com.fxnews.common.HtmlMapperUtils;
import com.fxnews.models.NewsProvider;
import lombok.Data;

@Data
public class VnExpressRepository implements Repository {

    private NewsProvider vnExpressProvider;

    public VnExpressRepository() {
        vnExpressProvider = NewsProvider.builder()
                .name("VnExpress")
                .url("https://vnexpress.net/")
                .categories(HtmlMapperUtils.categoriesStringArrayToMap(this.getProviderCategories()))
                .build();
    }

    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Newest (Mới nhất)", "https://vnexpress.net/rss/tin-moi-nhat.rss",
                "Covid (Covid)","https://vnexpress.net/rss/covid-19.rss",
                "Politics (Chính trị)", "https://vnexpress.net/rss/thoi-su.rss",
                "Business (Kinh doanh)", "https://vnexpress.net/rss/kinh-doanh.rss",
                "Technology (Công nghệ)", "https://vnexpress.net/rss/khoa-hoc.rss",
                "Health (Sức khoẻ)", "https://vnexpress.net/rss/suc-khoe.rss",
                "Sports (Thể thao)", "https://vnexpress.net/rss/the-thao.rss",
                "Entertainment (Giải trí)", "https://vnexpress.net/rss/giai-tri.rss",
                "World (Thế giới)", "https://vnexpress.net/rss/the-gioi.rss",
                "Others (Chuyên mục khác)","https://vnexpress.net/rss/tin-noi-bat.rss"
        };
    }

    @Override
    public String[] getCategoryNames() {
        return vnExpressProvider.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return vnExpressProvider.getCategories().get(categoryName);
    }

    @Override
    public NewsProvider get() {
        return vnExpressProvider;
    }
}
