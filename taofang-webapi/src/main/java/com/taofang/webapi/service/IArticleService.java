package com.taofang.webapi.service;

import com.taofang.webapi.domain.*;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public interface IArticleService {

    ArticlePaginationDomain getArticlePaginationDomain(int categoryId, int page, int pageSize);

    RitucharyaPaginationDomain getRitucharyaPaginationDomain(int ritucharya, int page, int pageSize);

    ArticlePaginationDomain getJKZSPaginationDomain(String queryDateStr, int page, int pageSize);

    ArticleDetailDomain getArticleDetailById(int categoryId, int articleId);

    boolean updateArticleThumb(ArticleThumbDomain articleThumbDomain);

    int getArticleThumb(int articleId);

    RitucharyaDomain getRitucharyaDomain(int ritucharya, int lastVideoId);
}
