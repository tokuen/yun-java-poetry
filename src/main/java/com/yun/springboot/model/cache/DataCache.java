package com.yun.springboot.model.cache;

import java.util.HashMap;
import java.util.Map;

public class DataCache {

    //诗歌类型缓存
    public static final Map<Integer, String> TYPE_NAME_CACHE = new HashMap<>();

    //public static final Map<Integer, String> SRC_CODE_CACHE = new HashMap<>();
    //1:h5 2:安卓 3ios 4:IPad 5:小程序 6:官网

    static {
        TYPE_NAME_CACHE.put(1, "散文");
        TYPE_NAME_CACHE.put(2, "随笔");
        TYPE_NAME_CACHE.put(3, "书籍");
        TYPE_NAME_CACHE.put(4, "歌词");
        TYPE_NAME_CACHE.put(5, "诗歌");
        TYPE_NAME_CACHE.put(6, "古诗");
        TYPE_NAME_CACHE.put(7, "宋词");
        TYPE_NAME_CACHE.put(8, "格言");
        TYPE_NAME_CACHE.put(9, "古风");
        TYPE_NAME_CACHE.put(10, "未知");
        TYPE_NAME_CACHE.put(11, "古文名句");
        TYPE_NAME_CACHE.put(12, "经典语录");
        TYPE_NAME_CACHE.put(13, "名人名言");
        TYPE_NAME_CACHE.put(14, "网友原创");
        TYPE_NAME_CACHE.put(15, "动漫语录");
        TYPE_NAME_CACHE.put(16, "电影台词");
        TYPE_NAME_CACHE.put(17, "电视剧台词");
        TYPE_NAME_CACHE.put(18, "小说摘抄");
        TYPE_NAME_CACHE.put(19, "名人传记");

    }
}
