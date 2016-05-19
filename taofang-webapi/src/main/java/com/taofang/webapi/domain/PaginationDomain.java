package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="pagination")
public class PaginationDomain {

    private int totalCount;

    private int page;

    private int pageSize;

    private int totalPage;

    public PaginationDomain() {
    }

    public PaginationDomain(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public PaginationDomain(int page, int pageSize, int totalCount) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
    }

    @XmlElement(name="totalCount")
    public int getTotalCount() {
        return totalCount;
    }
    @XmlElement(name="page")
    public int getPage() {
        return page;
    }
    @XmlElement(name="pageSize")
    public int getPageSize() {
        return pageSize;
    }
    @XmlElement(name="totalPage")
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
