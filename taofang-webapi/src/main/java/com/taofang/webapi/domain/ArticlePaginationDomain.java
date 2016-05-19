package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="articlePagination")
public class ArticlePaginationDomain {

    private PaginationDomain pagination;

    private List<ArticleDomain> articleList;

    public ArticlePaginationDomain() {
    }

    public ArticlePaginationDomain(PaginationDomain pagination, List<ArticleDomain> articleList) {
        this.pagination = pagination;
        this.articleList = articleList;
    }
    @XmlElement(name="pagination")
    public PaginationDomain getPagination() {
        return pagination;
    }
    @XmlElement(name="articleList")
    public List<ArticleDomain> getArticleList() {
        return articleList;
    }

    public void setPagination(PaginationDomain pagination) {
        this.pagination = pagination;
    }

    public void setArticleList(List<ArticleDomain> articleList) {
        this.articleList = articleList;
    }
}
