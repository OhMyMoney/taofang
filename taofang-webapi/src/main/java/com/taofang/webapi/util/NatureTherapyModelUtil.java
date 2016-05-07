package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.NatureTherapyInfo;
import com.taofang.webapi.domain.NatureTherapyInfoPagination;
import com.taofang.webapi.model.Article;

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

    public static NatureTherapyInfo tranArticle(Article article){
        NatureTherapyInfo natureTherapyInfo = new NatureTherapyInfo();
        natureTherapyInfo.setId(Optional.fromNullable(article.getArticleid()).or(0));
        natureTherapyInfo.setTitle(Optional.fromNullable(article.getArticlename()).or(""));

        return natureTherapyInfo;
    }

    public static List<NatureTherapyInfo> tranArticleList(List<Article> articleList){
        List<NatureTherapyInfo> natureTherapyInfoList = new ArrayList<>();
        for(Article article : articleList){
            natureTherapyInfoList.add(tranArticle(article));
        }

        return natureTherapyInfoList;
    }
}
