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


    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Trang chủ", "https://thanhnien.vn/",
                "Video", "https://thanhnien.vn/video/",
                "Thời sự", "https://thanhnien.vn/thoi-su/",
                "Thế giới", "https://thanhnien.vn/the-gioi/",
                "Tài chính - Kinh doanh", "https://thanhnien.vn/tai-chinh-kinh-doanh/",
                "Đời sống", "https://thanhnien.vn/doi-song/",
                "Văn hóa", "https://thanhnien.vn/van-hoa/",
                "Giải trí", "https://thanhnien.vn/giai-tri/",
                "Giới trẻ", "https://thanhnien.vn/gioi-tre/",
                "Giáo dục", "https://thanhnien.vn/giao-duc/",
                "Thể thao", "https://thanhnien.vn/the-thao/",
                "Sức khỏe", "https://thanhnien.vn/suc-khoe/",
                "Công nghệ - Game", "https://thanhnien.vn/cong-nghe-game/",
                "Xe", "https://thanhnien.vn/xe/",
                "Thời trang trẻ", "https://thoitrangtre.thanhnien.vn/",
                "Bạn đọc", "https://thanhnien.vn/ban-doc/",
        };
    }

}
