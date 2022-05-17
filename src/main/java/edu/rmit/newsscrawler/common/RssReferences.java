package edu.rmit.newsscrawler.common;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RssReferences {

    /* Thanh Nien RSS files */
    public static final String
        RSS_THANHNIEN_HOME = "http://thanhnien.vn/rss/home.rss",

        RSS_THANHNIEN_VIDEO = "http://thanhnien.vn/rss/video-316.rss",
        RSS_THANHNIEN_VIDEO_THOISU = "http://thanhnien.vn/rss/video/thoi-su-333.rss",
        RSS_THANHNIEN_VIDEO_PHONGSU = "http://thanhnien.vn/rss/video/phong-su-349.rss",
        RSS_THANHNIEN_VIDEO_GIAITRI = "http://thanhnien.vn/rss/video/giai-tri-334.rss",
        RSS_THANHNIEN_VIDEO_THETHAO = "http://thanhnien.vn/rss/video/the-thao-335.rss",
        RSS_THANHNIEN_VIDEO_MONNGON = "http://thanhnien.vn/rss/video/mon-ngon-350.rss",
        RSS_THANHNIEN_VIDEO_THEGIOI = "http://thanhnien.vn/rss/video/the-gioi-348.rss",
        RSS_THANHNIEN_VIDEO_TRUCTUYEN = "http://thanhnien.vn/rss/video/truc-tuyen-347.rss",

        RSS_THANHNIEN_THOISU = "http://thanhnien.vn/rss/thoi-su-4.rss",
        RSS_THANHNIEN_THOISU_CHINHTRI = "http://thanhnien.vn/rss/thoi-su/chinh-tri-4.rss",
        RSS_THANHNIEN_THOISU_PHAPLUAT = "http://thanhnien.vn/rss/thoi-su/phap-luat-5.rss",
        RSS_THANHNIEN_THOISU_DANSINH = "http://thanhnien.vn/rss/thoi-su/dan-sinh-6.rss",
        RSS_THANHNIEN_THOISU_LAODONGVIECLAM = "http://thanhnien.vn/rss/thoi-su/lao-dong-viec-lam-7.rss",
        RSS_THANHNIEN_THOISU_QUYENDUOCBIET = "http://thanhnien.vn/rss/thoi-su/quyen-duoc-biet-8.rss",
        RSS_THANHNIEN_THOISU_PHONGSUDIEUTRA = "http://thanhnien.vn/rss/thoi-su/phong-su-dieu-tra-9.rss",
        RSS_THANHNIEN_THOISU_QUOCPHONG = "http://thanhnien.vn/rss/thoi-su/quoc-phong-10.rss",
        RSS_THANHNIEN_THOISU_CHONGTINGIA = "http://thanhnien.vn/rss/thoi-su/chong-tin-gia-11.rss",

        RSS_THANHNIEN_GIAITRI = "http://thanhnien.vn/rss/giai-tri-3.rss",
        RSS_THANHNIEN_GIAITRI_PHIM = "http://thanhnien.vn/rss/giai-tri/phim-3.rss",
        RSS_THANHNIEN_GIAITRI_TRUYENHINH = "http://thanhnien.vn/rss/giai-tri/truyen-hinh-4.rss",
        RSS_THANHNIEN_GIAITRI_DOINGHESI = "http://thanhnien.vn/rss/giai-tri/doi-nghe-si-5.rss",

        RSS_THANHNIEN_VANHOA = "http://thanhnien.vn/rss/van-hoa-2.rss",
        RSS_THANHNIEN_VANHOA_CAUCHUYENVANHOA = "http://thanhnien.vn/rss/van-hoa/cau-chuyen-van-hoa-2.rss",
        RSS_THANHNIEN_VANHOA_KHAOCUU = "http://thanhnien.vn/rss/van-hoa/khao-cuu-3.rss",
        RSS_THANHNIEN_VANHOA_XEMNGHE = "http://thanhnien.vn/rss/van-hoa/xem-nghe-4.rss",
        RSS_THANHNIEN_VANHOA_HANOITHANHPHOTOIYEU = "http://thanhnien.vn/rss/van-hoa/ha-noi-thanh-pho-toi-yeu-5.rss",
        RSS_THANHNIEN_VANHOA_SONGDEP = "http://thanhnien.vn/rss/van-hoa/song-dep-6.rss",
        RSS_THANHNIEN_VANHOA_MONNGONHANOI = "http://thanhnien.vn/rss/van-hoa/mon-ngon-ha-noi-7.rss",

        RSS_THANHNIEN_THEGIOI = "http://thanhnien.vn/rss/the-gioi-1.rss",
        RSS_THANHNIEN_THEGIOI_KINHTETHEGIOI = "http://thanhnien.vn/rss/the-gioi/kinh-te-the-gioi-1.rss",
        RSS_THANHNIEN_THEGIOI_QUANSU = "http://thanhnien.vn/rss/the-gioi/quan-su-2.rss",
        RSS_THANHNIEN_THEGIOI_GOCNHIN = "http://thanhnien.vn/rss/the-gioi/goc-nhin-3.rss",
        RSS_THANHNIEN_THEGIOI_HOSO = "http://thanhnien.vn/rss/the-gioi/ho-so-4.rss",
        RSS_THANHNIEN_THEGIOI_NGUOIVIETNAMCHAU = "http://thanhnien.vn/rss/the-gioi/nguoi-viet-nam-chau-5.rss",
        RSS_THANHNIEN_THEGIOI_CHUYENLA = "http://thanhnien.vn/rss/the-gioi/chuyen-la-6.rss",

        RSS_THANHNIEN_CHAONGAYMOI = "http://thanhnien.vn/rss/chao-ngay-moi-1.rss",
        RSS_THANHNIEN_TOIVIET = "http://thanhnien.vn/rss/toi-viet-2.rss",
        RSS_THANHNIEN_DIENDAN = "http://thanhnien.vn/rss/dien-dan-3.rss",
        RSS_THANHNIEN_DIENDAN_VANHOAGIAOTHONG = "http://thanhnien.vn/rss/dien-dan/van-hoa-giao-thong-3.rss",

        RSS_THANHNIEN_THETHAO = "http://thanhnien.vn/rss/the-thao-1.rss",
        RSS_THANHNIEN_THETHAO_SEAGAMES31 = "http://thanhnien.vn/rss/the-thao/sea-games-31-1.rss",
        RSS_THANHNIEN_THETHAO_NHATIENTRISANCO = "http://thanhnien.vn/rss/the-thao/nha-tien-tri-san-co-2.rss",
        RSS_THANHNIEN_THETHAO_BONGDAVIETNAM = "http://thanhnien.vn/rss/the-thao/bong-da-viet-nam-3.rss",
        RSS_THANHNIEN_THETHAO_BONGDAQUOCTE = "http://thanhnien.vn/rss/the-thao/bong-da-quoc-te-4.rss",
        RSS_THANHNIEN_THETHAO_TINCHUYENNHUONG = "http://thanhnien.vn/rss/the-thao/tin-chuyen-nhuong-5.rss",
        RSS_THANHNIEN_THETHAO_BONGRO = "http://thanhnien.vn/rss/the-thao/bong-ro-6.rss",
        RSS_THANHNIEN_THETHAO_THETHAOCONGDONG = "http://thanhnien.vn/rss/the-thao/the-thao-cong-dong-7.rss",
        RSS_THANHNIEN_THETHAO_TOANCANHTHETHAO = "http://thanhnien.vn/rss/the-thao/toan-canh-the-thao-8.rss",

        RSS_THANHNIEN_DOISONG = "http://thanhnien.vn/rss/doi-song-1.rss",
        RSS_THANHNIEN_DOISONG_NGUOISONGQUANHTA = "http://thanhnien.vn/rss/doi-song/nguoi-song-quanh-ta-1.rss",
        RSS_THANHNIEN_DOISONG_GIADINH = "http://thanhnien.vn/rss/doi-song/gia-dinh-2.rss",
        RSS_THANHNIEN_DOISONG_AMTHUC = "http://thanhnien.vn/rss/doi-song/am-thuc-3.rss",
        RSS_THANHNIEN_DOISONG_DULICH = "http://thanhnien.vn/rss/doi-song/du-lich-4.rss",
        RSS_THANHNIEN_DOISONG_CONGDONG = "http://thanhnien.vn/rss/doi-song/cong-dong-5.rss",

        RSS_THANHNIEN_TAICHINHKINHDOANH = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh-1.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_KINHTEXANH = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/kinh-te-xanh-1.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_CHINHSACHPHATTRIEN = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/chinh-sach-phat-trien-2.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_NGANHANG = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/ngan-hang-3.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_CHUNGKHOAN = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/chung-khoan-4.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_DOANHNGHIEP = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/doanh-nghiep-5.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_DOANHNHAN = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/doanh-nhan-6.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_TIEUDUNG = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/tieu-dung-7.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_LAMGIAU = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/lam-giau-8.rss",
        RSS_THANHNIEN_TAICHINHKINHDOANH_DIAOC = "http://thanhnien.vn/rss/tai-chinh-kinh-doanh/dia-oc-9.rss";

    /* Nhan Dan RSS files */
    public static final String
        RSS_NHANDAN_HOME = "http://en.nhandan.vn/rss/home.rss",
        RSS_NHANDAN_VIDEO = "http://en.nhandan.vn/rss/video.rss",
        RSS_NHANDAN_POLITICS = "http://en.nhandan.vn/rss/politics.rss",
        RSS_NHANDAN_BUSINESS = "http://en.nhandan.vn/rss/business.rss",
        RSS_NHANDAN_CULTURE = "http://en.nhandan.vn/rss/culture.rss",
        RSS_NHANDAN_SPORTS = "http://en.nhandan.vn/rss/sports.rss",
        RSS_NHANDAN_SCI_TECH_ENV = "http://en.nhandan.vn/rss/scitech.rss",
        RSS_NHANDAN_TRAVEL = "http://en.nhandan.vn/rss/travel.rss",
        RSS_NHANDAN_WORLD = "http://en.nhandan.vn/rss/world.rss";

    /* Vnexpress RSS files */
    public static final String
        RSS_VNEXPRESS_HOME = "http://vnexpress.net/rss/tin-moi-nhat.rss",
        RSS_VNEXPRESS_THEGIOI = "http://vnexpress.net/rss/the-gioi.rss",
        RSS_VNEXPRESS_THOISU = "http://vnexpress.net/rss/thoi-su.rss",
        RSS_VNEXPRESS_KINHDOANH = "http://vnexpress.net/rss/kinh-doanh.rss",
        RSS_VNEXPRESS_STARTUP = "http://vnexpress.net/rss/startup.rss",
        RSS_VNEXPRESS_GIAITRI = "http://vnexpress.net/rss/giai-tri.rss",
        RSS_VNEXPRESS_THETHAO = "http://vnexpress.net/rss/the-thao.rss",
        RSS_VNEXPRESS_PHAPLUAT = "http://vnexpress.net/rss/phap-luat.rss",
        RSS_VNEXPRESS_GIAODUC = "http://vnexpress.net/rss/giao-duc.rss",
        RSS_VNEXPRESS_TINNOIBAT = "http://vnexpress.net/rss/tin-noi-bat.rss",
        RSS_VNEXPRESS_SUCKHOE = "http://vnexpress.net/rss/suc-khoe.rss",
        RSS_VNEXPRESS_DOISONG = "http://vnexpress.net/rss/doi-song.rss",
        RSS_VNEXPRESS_DULICH = "http://vnexpress.net/rss/du-lich.rss",
        RSS_VNEXPRESS_KHOAHOC = "http://vnexpress.net/rss/khoa-hoc.rss",
        RSS_VNEXPRESS_SOHOA = "http://vnexpress.net/rss/so-hoa.rss",
        RSS_VNEXPRESS_XE = "http://vnexpress.net/rss/oto-xe-may.rss",
        RSS_VNEXPRESS_YKIEN = "http://vnexpress.net/rss/y-kien.rss",
        RSS_VNEXPRESS_TAMSU = "http://vnexpress.net/rss/tam-su.rss",
        RSS_VNEXPRESS_CUOI = "http://vnexpress.net/rss/cuoi.rss",
        RSS_VNEXPRESS_TINXEMNHIEU = "http://vnexpress.net/rss/tin-xem-nhieu.rss";

    public static final Map<String, String> getRssMap() {
        Map<String, String> rssMap = new HashMap<String, String>();

        RssReferences rssReferences = new RssReferences();

        return Arrays.stream(rssReferences.getClass().getFields())
                     .collect(Collectors.toMap(Field::getName, (field) -> {
            try {
                return (String) field.get(rssReferences);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public static final String getRssUrl(String rssName) {
        Map<String, String> rssMap = getRssMap();

        if (rssMap.containsKey(rssName)) {
            return rssMap.get(rssName);
        } else {
            throw new IllegalArgumentException("RSS name " + rssName + " is not available");
        }
    }

}
