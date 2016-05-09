package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="ritucharyaPagination")
public class RitucharyaPagination {
    // 总的数量
    private int totalCount;
    // 当前页
    private int curPage;
    // 每页数量
    private int perPage;
    // 总共页数
    private int totalPage;
    // 当前页数据
    private List<Ritucharya> ritucharyaList;

    @XmlElement(name="totalCount")
    public int getTotalCount() {
        return totalCount;
    }
    @XmlElement(name="curPage")
    public int getCurPage() {
        return curPage;
    }
    @XmlElement(name="perPage")
    public int getPerPage() {
        return perPage;
    }
    @XmlElement(name="totalPage")
    public int getTotalPage() {
        return totalPage;
    }
    @XmlElement(name="ritucharyas")
    public List<Ritucharya> getRitucharyaList() {
        return ritucharyaList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setRitucharyaList(List<Ritucharya> ritucharyaList) {
        this.ritucharyaList = ritucharyaList;
    }
}