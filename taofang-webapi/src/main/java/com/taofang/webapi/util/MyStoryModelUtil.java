package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.domain.StoryInfoPagination;
import com.taofang.webapi.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
public class MyStoryModelUtil {

    public static StoryInfoPagination tranTotalCount(int page, int pageSize, int totalCount){
        StoryInfoPagination storyInfoPagination = new StoryInfoPagination();
        storyInfoPagination.setCurPage(page);
        storyInfoPagination.setPerPage(pageSize);
        storyInfoPagination.setTotalCount(totalCount);
        storyInfoPagination.setTotalPage(PaginationUtil.getTotalPage(totalCount, pageSize));

        return storyInfoPagination;
    }

    public static StoryInfo tranArticle(Article article){
        StoryInfo storyInfo = new StoryInfo();
        storyInfo.setId(Optional.fromNullable(article.getArticleid()).or(0));
        storyInfo.setTitle(Optional.fromNullable(article.getArticlename()).or(""));
        // 文章图片
        if(!Strings.isNullOrEmpty(article.getImageurl())){
            storyInfo.setImageUrl(ImageConstant.IMAGE_BASE_URL + "Article/" + article.getImageurl());
        }
        // 文章内容
        String articleContent = article.getArticlecontent();
        if(!Strings.isNullOrEmpty(articleContent)){
            articleContent = articleContent.substring(articleContent.indexOf("<h"));
            int videoIndex = articleContent.indexOf("</object></p>");
            if(videoIndex > 0){
                // mp3
                String videoContent = articleContent.substring(0, videoIndex);
                int srcIndex = videoContent.indexOf("src=");
                int typeIndex = videoContent.indexOf("type=");
                String videoUrl = videoContent.substring(srcIndex+4, typeIndex).trim().replaceAll("\"", "");
                storyInfo.setVideoUrl(videoUrl);
                articleContent = articleContent.substring(videoIndex + 13);
            }else{
                storyInfo.setVideoUrl("");
            }
            articleContent = articleContent.substring(articleContent.indexOf("<p"));
            articleContent = articleContent.replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL);
            storyInfo.setContent(articleContent);
        }
        // 点赞数
        storyInfo.setThumbCount((int) (Math.random() * 100));
        return storyInfo;
    }

    public static List<StoryInfo> tranArticleList(List<Article> articleList){
        List<StoryInfo> storyInfoList = new ArrayList<>();
        for(Article article : articleList){
            storyInfoList.add(tranArticle(article));
        }

        return storyInfoList;
    }
}
