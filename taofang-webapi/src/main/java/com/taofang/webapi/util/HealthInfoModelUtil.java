package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.HealthInfo;
import com.taofang.webapi.domain.HealthInfoPagination;
import com.taofang.webapi.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public class HealthInfoModelUtil {

    public static HealthInfoPagination tranTotalCount(int page, int pageSize, int totalCount){
        HealthInfoPagination healthInfoPagination = new HealthInfoPagination();
        healthInfoPagination.setCurPage(page);
        healthInfoPagination.setPerPage(pageSize);
        healthInfoPagination.setTotalCount(totalCount);
        healthInfoPagination.setTotalPage(PaginationUtil.getTotalPage(totalCount, pageSize));

        return healthInfoPagination;
    }

    public static HealthInfo tranArticle(Article article){
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.setId(Optional.fromNullable(article.getArticleid()).or(0));
        healthInfo.setTitle(Optional.fromNullable(article.getArticlename()).or(""));

        return healthInfo;
    }

    public static List<HealthInfo> tranArticleList(List<Article> articleList){
        List<HealthInfo> healthInfoList = new ArrayList<>();
        for(Article article : articleList){
            healthInfoList.add(tranArticle(article));
        }

        return healthInfoList;
    }
}
