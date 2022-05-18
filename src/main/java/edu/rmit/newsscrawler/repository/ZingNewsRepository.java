package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.common.HtmlMapperUtils;
import edu.rmit.newsscrawler.models.NewsProvider;

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

    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Trang chủ", "https://zingnews.vn/",
                "Xuất bản", "https://zingnews.vn/xuat-ban.html",
                "Thời sự", "https://zingnews.vn/thoi-su.html",
                "Thế giới", "https://zingnews.vn/the-gioi.html",
                "Kinh doanh", "https://zingnews.vn/kinh-doanh-tai-chinh.html",
                "Công nghệ", "https://zingnews.vn/cong-nghe.html",
                "Sức khỏe", "https://zingnews.vn/suc-khoe.html",
                "Thể thao", "https://zingnews.vn/the-thao.html",
                "Giải trí", "https://zingnews.vn/giai-tri.html",
                "Đời sống", "https://zingnews.vn/doi-song.html",
                "Du lịch", "https://zingnews.vn/du-lich.html",
                "Lifestyle", "https://zingnews.vn/lifestyle.html"
        };
    }
}
