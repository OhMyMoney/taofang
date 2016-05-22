package com.taofang.webapi.service;

import com.taofang.webapi.domain.WordSearchPaginationDomain;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
public interface IWordSearchService {
    WordSearchPaginationDomain getWordSearchPaginationDomain(int page, int pageSize);
}
