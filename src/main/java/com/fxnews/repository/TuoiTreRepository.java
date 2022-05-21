package com.fxnews.repository;

import com.fxnews.common.HtmlMapperUtils;
import com.fxnews.models.NewsProvider;

public class TuoiTreRepository implements Repository {

    private NewsProvider tuoiTreProvider;

    public TuoiTreRepository() {
        tuoiTreProvider = NewsProvider.builder()
                .name("Tuổi trẻ")
                .url("https://tuoitre.vn/")
                .categories(HtmlMapperUtils.categoriesStringArrayToMap(this.getProviderCategories()))
                .build();
    }

    @Override
    public String[] getProviderCategories() {
        return new String[]{
                "Newest (Mới nhất)", "https://tuoitre.vn/rss/tin-moi-nhat.rss",
//                "Codvid (Covid)","https://tuoitre.vn/rss/covid-19.rss",
                "Politics (Chính trị)", "https://tuoitre.vn/rss/thoi-su.rss",
                "Business (Kinh doanh)", "https://tuoitre.vn/rss/kinh-doanh.rss",
                "Technology (Công nghệ)", "https://tuoitre.vn/rss/khoa-hoc.rss",
                "Health (Sức khoẻ)", "https://tuoitre.vn/rss/suc-khoe.rss",
                "Sports (Thể thao)", "https://tuoitre.vn/rss/the-thao.rss",
                "Entertainment (Giải trí)", "https://tuoitre.vn/rss/giai-tri.rss",
                "World (Thế giới)", "https://tuoitre.vn/rss/the-gioi.rss",
                "Others (Chuyên mục khác)","https://tuoitre.vn/rss/ban-doc-lam-bao.rss"
        };
    }

    @Override
    public String[] getCategoryNames() {
        return tuoiTreProvider.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return tuoiTreProvider.getCategories().get(categoryName);
    }

    @Override
    public NewsProvider get() {
        return tuoiTreProvider;
    }
}

