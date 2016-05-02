package com.taofang.webapi.util;

import com.google.common.base.Optional;
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
public class StoryInfoModelUtil {

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
