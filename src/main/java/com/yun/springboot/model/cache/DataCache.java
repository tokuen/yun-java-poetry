package com.yun.springboot.model.cache;

import java.util.HashMap;
import java.util.Map;

public class DataCache {

    //诗歌类型缓存
    public static final Map<Integer, String> TYPENAMECACHE = new HashMap<>();

    static {
        TYPENAMECACHE.put(1, "散文");
        TYPENAMECACHE.put(2, "随笔");
        TYPENAMECACHE.put(3, "书籍");
        TYPENAMECACHE.put(4, "歌词");
        TYPENAMECACHE.put(5, "诗歌");
        TYPENAMECACHE.put(6, "古诗");
        TYPENAMECACHE.put(7, "宋词");
        TYPENAMECACHE.put(8, "格言");
        TYPENAMECACHE.put(9, "古风");
        TYPENAMECACHE.put(10, "未知");
        TYPENAMECACHE.put(11, "古文名句");
        TYPENAMECACHE.put(12, "经典语录");
        TYPENAMECACHE.put(13, "名人名言");
        TYPENAMECACHE.put(14, "网友原创");
        TYPENAMECACHE.put(15, "动漫语录");
        TYPENAMECACHE.put(16, "电影台词");
        TYPENAMECACHE.put(17, "电视剧台词");
        TYPENAMECACHE.put(18, "小说摘抄");
        TYPENAMECACHE.put(19, "名人传记");
    }
}
