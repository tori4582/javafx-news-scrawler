package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.common.HtmlMapperUtils;
import edu.rmit.newsscrawler.models.Category;
import edu.rmit.newsscrawler.models.NewsProvider;
import lombok.Data;

import java.util.List;
import java.util.Map;

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
    public NewsProvider get() {
        return vnExpressProvider;
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
    public String[] getProviderCategories() {
        return new String[] {
                "Trang chủ", "https://vnexpress.net/",
                "Mới nhất", "https://vnexpress.net/tin-tuc-24h",
                "Thời sự", "https://vnexpress.net/thoi-su",
                "Góc nhìn", "https://vnexpress.net/goc-nhin",
                "Thế giới", "https://vnexpress.net/the-gioi",
                "Video", "https://video.vnexpress.net/",
                "Podcasts", "https://vnexpress.net/podcast",
                "Kinh doanh", "https://vnexpress.net/kinh-doanh",
                "Khoa học", "https://vnexpress.net/khoa-hoc",
                "Giải trí", "https://vnexpress.net/giai-tri",
                "Thể thao", "https://vnexpress.net/the-thao",
                "Pháp luật", "https://vnexpress.net/phap-luat",
                "Giáo dục", "https://vnexpress.net/giao-duc",
                "Sức khỏe", "https://vnexpress.net/suc-khoe",
                "Đời sống", "https://vnexpress.net/doi-song",
                "Du lịch", "https://vnexpress.net/du-lich",
                "Số hóa", "https://vnexpress.net/so-hoa",
                "Xe", "https://vnexpress.net/oto-xe-may",
                "Ý kiến", "https://vnexpress.net/y-kien",
                "Tâm sự", "https://vnexpress.net/tam-su",
                "Hài", "https://vnexpress.net/hai",
        };
    }

}
