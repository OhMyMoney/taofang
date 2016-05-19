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
public class RitucharyaPaginationDomain {

    private PaginationDomain pagination;

    private List<RitucharyaDomain> ritucharyaList;

    public RitucharyaPaginationDomain() {
    }

    public RitucharyaPaginationDomain(PaginationDomain pagination, List<RitucharyaDomain> ritucharyaList) {
        this.pagination = pagination;
        this.ritucharyaList = ritucharyaList;
    }
    @XmlElement(name="pagination")
    public PaginationDomain getPagination() {
        return pagination;
    }
    @XmlElement(name="ritucharyaList")
    public List<RitucharyaDomain> getRitucharyaList() {
        return ritucharyaList;
    }

    public void setPagination(PaginationDomain pagination) {
        this.pagination = pagination;
    }

    public void setRitucharyaList(List<RitucharyaDomain> ritucharyaList) {
        this.ritucharyaList = ritucharyaList;
    }
}
