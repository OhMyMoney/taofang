package com.taofang.webapi.constant;

import com.google.common.base.Optional;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public enum  ArticleCategory {
    WDGS(0, "WDGS", "我的故事"),
    JKZX(1, "JKZX", "健康资讯"),
    ZRLF(2, "ZRLF", "自然疗法"),
    ZWCS(3, "ZWCS", "自我测试"),
    JKZS(4, "JKZS", "健康之声"),
    PTWZ(5, "PTWZ", "普通文章"),
    YDFA(6, "YDFA", "运动方案"),
    JJYS(7, "JJYS", "季节养生");

    public int category;

    public String categoryName;

    public String categoryDesc;

    ArticleCategory(int category, String categoryName, String categoryDesc) {
        this.category = category;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public static int getCategoryIdByName(String categoryName){
        int category = -1;
        for(ArticleCategory articleCategory : ArticleCategory.values()){
            if(Optional.fromNullable(categoryName).or("").equals(articleCategory.categoryName)){
                category = articleCategory.category;
                break;
            }
        }
        return category;
    }

    public static String getCategoryNameById(int category){
        String categoryName = "";
        for(ArticleCategory articleCategory : ArticleCategory.values()){
            if(articleCategory.category == category){
                categoryName = articleCategory.categoryName;
                break;
            }
        }
        return categoryName;
    }

    public static ArticleCategory getArticleCategoryById(int category){
        ArticleCategory article = ArticleCategory.WDGS;
        for(ArticleCategory articleCategory : ArticleCategory.values()){
            if(articleCategory.category == category){
                article = articleCategory;
                break;
            }
        }
        return article;
    }
}
