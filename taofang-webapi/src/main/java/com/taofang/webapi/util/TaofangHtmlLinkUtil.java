package com.taofang.webapi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-16
 */
public class TaofangHtmlLinkUtil {
    private static final Map<String, String> linkMap;

    static {
        linkMap = new HashMap<>();
        // 良方
        linkMap.put("liangfang_list", "/views/liangfang/list.html");
        linkMap.put("liangfang_detail", "/views/liangfang/detail.html");
        // 健康之声
        linkMap.put("jkzs_list", "/views/jkzs/list.html");
        linkMap.put("jkzs_detail", "/views/jkzs/detail.html");
        // 我的故事
        linkMap.put("wdgs_list", "/views/wdgs/list.html");
        linkMap.put("wdgs_detail", "/views/wdgs/detail.html");
        // 自然疗法
        linkMap.put("zrlf_list", "/views/zrlf/list.html");
        linkMap.put("zrlf_detail", "/views/zrlf/detail.html");
        // 季节养生
        linkMap.put("jjys_list", "/views/jjys/list.html");
        linkMap.put("jjys_detail", "/views/jjys/detail.html");
        // 健康资讯
        linkMap.put("jkzx_list", "/views/jkzx/list.html");
        linkMap.put("jkzx_detail", "/views/jkzx/detail.html");
    }

    public static String getHtmlLinkByModule(String moduleName, String moduleType){
        String key = moduleName + "_" + moduleType;
        String link = linkMap.get(key);
        return link == null ? "" : link;
    }
}
