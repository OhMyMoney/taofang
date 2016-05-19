package com.taofang.webapi.service;

import com.taofang.webapi.domain.ArticlePaginationDomain;
import com.taofang.webapi.domain.RitucharyaPaginationDomain;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public interface IArticleService {

    ArticlePaginationDomain getArticlePaginationDomain(int categoryId, int page, int pageSize);

    RitucharyaPaginationDomain getRitucharyaPaginationDomain(int ritucharya, int page, int pageSize);

    ArticlePaginationDomain getJKZSPaginationDomain(String queryDateStr, int page, int pageSize);
}
