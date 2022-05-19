package edu.rmit.newsscrawler.repository;

import edu.rmit.newsscrawler.common.HtmlMapperUtils;
import edu.rmit.newsscrawler.models.NewsProvider;

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
    public NewsProvider get() {
        return tuoiTreProvider;
    }

    @Override
    public String[] getCategoryNames() {
        return tuoiTreProvider.getCategories().keySet().toArray(new String[0]);
    }

    @Override
    public String getCategoryUrl(String categoryName) {
        return tuoiTreProvider.getCategories().get(categoryName);
    }

//    @Override
//    public String[] getProviderCategories() {
//        return new String[] {
//                "Trang chủ", "https://vnexpress.net/",
//                "Mới nhất", "https://vnexpress.net/tin-tuc-24h",
//                "Media", "https://tuoitre.vn/media.htm",
//                "Thời sự", "https://tuoitre.vn/thoi-su.htm",
//                "Thế giới", "https://tuoitre.vn/the-gioi.htm",
//                "Pháp luật", "https://tuoitre.vn/phap-luat.htm",
//                "Kinh doanh", "https://tuoitre.vn/kinh-doanh.htm",
//                "Công nghệ", "https://congnghe.tuoitre.vn/",
//                "Xe", "https://tuoitre.vn/xe.htm",
//                "Du lịch", "https://dulich.tuoitre.vn/",
//                "Nhịp sống trẻ", "https://tuoitre.vn/nhip-song-tre.htm",
//                "Văn hóa", "https://tuoitre.vn/van-hoa.htm",
//                "Giải trí", "https://tuoitre.vn/giai-tri.htm",
//                "Thể thao", "https://tuoitre.vn/the-thao.htm",
//                "Giáo dục", "https://tuoitre.vn/giao-duc.htm",
//                "Khoa học", "https://tuoitre.vn/khoa-hoc.htm",
//                "Sức khỏe", "https://tuoitre.vn/suc-khoe.htm",
//                "Giả-thật", "https://tuoitre.vn/gia-that.htm",
//                "Bạn đọc", "https://tuoitre.vn/ban-doc-lam-bao.htm",
//        };

    @Override
    public String[] getProviderCategories() {
        return new String[]{
                "Trang chủ", "https://tuoitre.vn/rss/tin-moi-nhat.rss",
                "Thời sự", "https://tuoitre.vn/rss/thoi-su.rss",
                "Thế giới", "https://tuoitre.vn/rss/the-gioi.rss",
                "Pháp luật", "https://tuoitre.vn/rss/phap-luat.rss",
                "Kinh doanh", "https://tuoitre.vn/rss/kinh-doanh.rss",
                "Công nghệ", "https://tuoitre.vn/rss/nhip-song-so.rss",
                "Xe", "https://tuoitre.vn/rss/xe.rss",
                "Nhịp sống trẻ", "https://tuoitre.vn/rss/nhip-song-tre.rss",
                "Văn hóa", "https://tuoitre.vn/rss/van-hoa.rss",
                "Giải trí", "https://tuoitre.vn/rss/giai-tri.rss",
                "Thể thao", "https://tuoitre.vn/rss/the-thao.rss",
                "Giáo dục", "https://tuoitre.vn/rss/giao-duc.rss",
                "Khoa học", "https://tuoitre.vn/rss/khoa-hoc.rss",
                "Sức khỏe", "https://tuoitre.vn/rss/suc-khoe.rss",
                "Giả thật", "https://tuoitre.vn/rss/gia-that.rss",
                "Thư giãn", "https://tuoitre.vn/rss/thu-gian.rss",
                "Bạn đọc làm báo", "https://tuoitre.vn/rss/ban-doc-lam-bao.rss",
                "Du lịch", "https://tuoitre.vn/rss/du-lich.rss"
        };
    }
}

