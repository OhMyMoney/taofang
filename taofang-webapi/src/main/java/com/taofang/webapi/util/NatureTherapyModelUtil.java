package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.NatureTherapyInfo;
import com.taofang.webapi.domain.NatureTherapyInfoPagination;
import com.taofang.webapi.model.ArticleWithBLOBs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-04
 */
public class NatureTherapyModelUtil {

    public static NatureTherapyInfoPagination tranTotalCount(int page, int pageSize, int totalCount){
        NatureTherapyInfoPagination natureTherapyInfoPagination = new NatureTherapyInfoPagination();
        natureTherapyInfoPagination.setCurPage(page);
        natureTherapyInfoPagination.setPerPage(pageSize);
        natureTherapyInfoPagination.setTotalCount(totalCount);
        natureTherapyInfoPagination.setTotalPage(PaginationUtil.getTotalPage(totalCount, pageSize));

        return natureTherapyInfoPagination;
    }

    public static NatureTherapyInfo tranArticle(ArticleWithBLOBs article){
        NatureTherapyInfo natureTherapyInfo = new NatureTherapyInfo();
        natureTherapyInfo.setId(Optional.fromNullable(article.getArticleid()).or(0));
        natureTherapyInfo.setTitle(Optional.fromNullable(article.getArticlename()).or(""));
        // 文章图片
        if(!Strings.isNullOrEmpty(article.getImageurl())){
            natureTherapyInfo.setImageUrl(ImageConstant.IMAGE_BASE_URL + "Article/" + article.getImageurl());
        }
        // 文章内容
        String articleContent = article.getArticlecontent();
        if(!Strings.isNullOrEmpty(articleContent)){
            articleContent = articleContent.substring(articleContent.indexOf("<h"));
            int videoIndex = articleContent.indexOf("</object></p>");
            if(videoIndex > 0){
                // video
                String videoContent = articleContent.substring(0, videoIndex);
                int srcIndex = videoContent.indexOf("src=");
                int typeIndex = videoContent.indexOf("type=");
                String videoUrl = videoContent.substring(srcIndex+4, typeIndex).trim().replaceAll("\"", "");
                natureTherapyInfo.setVideoUrl(videoUrl);
                articleContent = articleContent.substring(videoIndex + 13);
            }else{
                natureTherapyInfo.setVideoUrl("");
            }
            articleContent = articleContent.substring(articleContent.indexOf("<p"));
            articleContent = articleContent.replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL);
            natureTherapyInfo.setContent(articleContent);
        }
        // 点赞数
        natureTherapyInfo.setThumbCount((int) (Math.random() * 100));
        return natureTherapyInfo;
    }

    public static List<NatureTherapyInfo> tranArticleList(List<ArticleWithBLOBs> articleList){
        List<NatureTherapyInfo> natureTherapyInfoList = new ArrayList<>();
        for(ArticleWithBLOBs article : articleList){
            natureTherapyInfoList.add(tranArticle(article));
        }

        return natureTherapyInfoList;
    }
}
