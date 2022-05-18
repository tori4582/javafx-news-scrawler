package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.common.HtmlMapperUtils;
import edu.rmit.newsscrawler.models.NewsProvider;

public class NhanDanRepository implements Repository {

    private NewsProvider nhanDanProvider;

    public NhanDanRepository() {
        nhanDanProvider = NewsProvider.builder()
                .name("Nhân Dân")
                .url("https://nhandan.vn/")
                .categories(HtmlMapperUtils.categoriesStringArrayToMap(this.getProviderCategories()))
                .build();
    }

    @Override
    public String[] getProviderCategories() {
        return new String[] {
                "Trang chủ", "https://nhandan.vn/",
                "Chính trị", "https://nhandan.vn/chinhtri",
                "Kinh tế", "https://nhandan.vn/kinhte",
                "Văn hóa", "https://nhandan.vn/vanhoa",
                "Xã hội", "https://nhandan.vn/xahoi",
                "Pháp luật", "https://nhandan.vn/phapluat",
                "Du lịch", "https://nhandan.vn/du-lich",
                "Thế giới", "https://nhandan.vn/thegioi",
                "Thể thao", "https://nhandan.vn/thethao",
                "Giáo dục", "https://nhandan.vn/giaoduc",
                "Y tế", "https://nhandan.vn/y-te",
                "Khoa học - Công nghệ", "https://nhandan.vn/khoahoc-congnghe",
                "Môi trường", "https://nhandan.vn/moi-truong",
                "Bạn đọc", "https://nhandan.vn/bandoc",
                "RADIO", "https://radio.nhandan.vn/",
        };
    }

    @Override
    public String[] getCategoryNames() {
        return nhanDanProvider.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return nhanDanProvider.getCategories().get(categoryName);
    }

    @Override
    public NewsProvider get() {
        return this.nhanDanProvider;
    }
}
