package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="wordSearchPagination")
public class WordSearchPaginationDomain {

    private PaginationDomain pagination;

    private List<WordSearchDomain> wordSearchList;

    public WordSearchPaginationDomain() {
    }

    public WordSearchPaginationDomain(PaginationDomain pagination, List<WordSearchDomain> wordSearchList) {
        this.pagination = pagination;
        this.wordSearchList = wordSearchList;
    }

    @XmlElement(name="pagination")
    public PaginationDomain getPagination() {
        return pagination;
    }
    @XmlElement(name="wordSearchList")
    public List<WordSearchDomain> getWordSearchList() {
        return wordSearchList;
    }

    public void setPagination(PaginationDomain pagination) {
        this.pagination = pagination;
    }

    public void setWordSearchList(List<WordSearchDomain> wordSearchList) {
        this.wordSearchList = wordSearchList;
    }
}
