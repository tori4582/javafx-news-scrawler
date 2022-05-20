package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.common.HtmlMapperUtils;
import edu.rmit.newsscrawler.models.NewsProvider;

public class ThanhNienRepository implements Repository {

    private NewsProvider thanhNienRepository;


    public ThanhNienRepository() {
        thanhNienRepository = NewsProvider.builder()
                .name("Thanh niên")
                .url("https://thanhnien.vn/")
                .categories(HtmlMapperUtils.categoriesStringArrayToMap(this.getProviderCategories()))
                .build();
    }

    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Newest (Mới nhất)", "https://thanhnien.vn/rss/home.rss",
                "Politics (Chính trị)", "https://thanhnien.vn/rss/thoi-su/chinh-tri.rss",
                "Business (Kinh doanh)", "https://thanhnien.vn/rss/tai-chinh-kinh-doanh-49.rss",
                "Technology (Công nghệ)", "https://thanhnien.vn/rss/cong-nghe-game-315.rss",
                "Health (Sức khoẻ)", "https://thanhnien.vn/rss/suc-khoe-65.rss",
                "Sports (Thể thao)", "https://thanhnien.vn/rss/the-thao-318.rss",
                "Entertainment (Giải trí)", "https://thanhnien.vn/rss/giai-tri-285.rss",
                "World (Thế giới)", "https://thanhnien.vn/rss/the-gioi-66.rss",
                "Others (Chuyên mục khác)", "https://thanhnien.vn/rss/dien-dan-561.rss",
        };
    }

    @Override
    public String[] getCategoryNames() {
        return thanhNienRepository.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return thanhNienRepository.getCategories().get(categoryName);
    }

    @Override
    public NewsProvider get() {
        return this.thanhNienRepository;
    }
}
