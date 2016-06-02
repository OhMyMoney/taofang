package com.taofang.webapi.constant;

import com.google.common.base.Optional;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public enum AdvertisementCategory {
    LF_LIST(0, "LF_LIST", "良方搜索结果"),
    LF_DETAIL(1, "LF_DETAIL", "偏方详情"),
    QFXF(2, "QFXF", "求方与献方"),
    WDGS(3, "WDGS", "我的故事"),
    JKZX(4, "JKZX", "健康资讯"),
    ZRLF(5, "ZRLF", "自然疗法"),
    ZWCS(6, "ZWCS", "自我测试"),
    JKZS(7, "JKZS", "健康之声"),
    JJYS(8, "JJYS", "季节养生");

    public int category;

    public String categoryName;

    public String categoryDesc;

    AdvertisementCategory(int category, String categoryName, String categoryDesc) {
        this.category = category;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public static int getCategoryIdByName(String categoryName){
        int category = -1;
        for(AdvertisementCategory articleCategory : AdvertisementCategory.values()){
            if(Optional.fromNullable(categoryName).or("").equals(articleCategory.categoryName)){
                category = articleCategory.category;
                break;
            }
        }
        return category;
    }

    public static String getCategoryNameById(int category){
        String categoryName = "";
        for(AdvertisementCategory articleCategory : AdvertisementCategory.values()){
            if(articleCategory.category == category){
                categoryName = articleCategory.categoryName;
                break;
            }
        }
        return categoryName;
    }

    public static AdvertisementCategory getArticleCategoryById(int category){
        AdvertisementCategory article = AdvertisementCategory.LF_LIST;
        for(AdvertisementCategory articleCategory : AdvertisementCategory.values()){
            if(articleCategory.category == category){
                article = articleCategory;
                break;
            }
        }
        return article;
    }
}
