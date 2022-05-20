package edu.rmit.fxnews.repository;

import edu.rmit.fxnews.common.HtmlMapperUtils;
import edu.rmit.fxnews.models.NewsProvider;

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
                "Newest (Mới nhất)", "https://nhandan.vn/",
                "Politics (Chính trị)", "https://nhandan.vn/chinhtri",
                "Business (Kinh doanh)", "https://nhandan.vn/kinhte",
                "Technology (Công nghệ)", "https://nhandan.vn/khoahoc-congnghe",
                "Health (Sức khoẻ)", "https://nhandan.vn/y-te",
                "Sports (Thể thao)", "https://nhandan.vn/thethao",
                "Entertainment (Giải trí)", "https://nhandan.vn/du-lich",
                "World (Thế giới)", "https://nhandan.vn/thegioi",
                "Others (Chuyên mục khác)", "https://nhandan.vn/xahoi",
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
