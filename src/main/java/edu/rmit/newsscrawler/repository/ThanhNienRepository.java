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
                "Trang chủ", "https://thanhnien.vn/rss/home.rss",
                "Video", "https://thanhnien.vn/rss/video-316.rss",
                "Thời sự", "https://thanhnien.vn/rss/thoi-su-4.rss",
                "Chào ngày mới", "https://thanhnien.vn/rss/chao-ngay-moi-2.rss",
                "Tôi viết", "https://thanhnien.vn/rss/toi-viet-89.rss",
                "Diễn đàn", "https://thanhnien.vn/rss/dien-dan-561.rss",
                "Thế giới", "https://thanhnien.vn/rss/the-gioi-66.rss",
                "Văn hóa", "https://thanhnien.vn/rss/van-hoa-93.rss",
                "Giải trí", "https://thanhnien.vn/rss/giai-tri-285.rss",
                "Thể thao", "https://thanhnien.vn/rss/the-thao-318.rss",
                "Đời sống", "https://thanhnien.vn/rss/doi-song-17.rss",
                "Tài chính - Kinh doanh", "https://thanhnien.vn/rss/tai-chinh-kinh-doanh-49.rss",
                "Giới trẻ", "https://thanhnien.vn/rss/gioi-tre-69.rss",
                "Giáo dục", "https://thanhnien.vn/rss/giao-duc-26.rss",
                "Công nghệ - Game", "https://thanhnien.vn/rss/cong-nghe-game-315.rss",
                "Sức khỏe", "https://thanhnien.vn/rss/suc-khoe-65.rss",
                "Rao vặt", "https://thanhnien.vn/rss/rao-vat-573.rss",
                "Xe", "https://thanhnien.vn/rss/xe-317.rss",
                "Thời trang trẻ", "https://thanhnien.vn/rss/thoi-trang-tre-319.rss",
                "Bạn đọc", "https://thanhnien.vn/rss/ban-doc-190.rss",
                "Bạn cần biết", "https://thanhnien.vn/rss/ban-can-biet-153.rss",
        };
    }

}
