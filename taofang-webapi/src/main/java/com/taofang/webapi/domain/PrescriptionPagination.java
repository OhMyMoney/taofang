package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescriptionPagination")
public class PrescriptionPagination {
    // 总的数量
    private int totalCount;
    // 当前页
    private int curPage;
    // 每页数量
    private int perPage;
    // 总共页数
    private int totalPage;
    // 当前页数据
    private List<Prescription> prescriptionList;

    public PrescriptionPagination() {
    }

    public PrescriptionPagination(int totalCount, List<Prescription> prescriptionList) {
        this.totalCount = totalCount;
        this.prescriptionList = prescriptionList;
    }

    @Override
    public String toString() {
        return "PrescriptionPagination{" +
                "totalCount=" + totalCount +
                ", curPage=" + curPage +
                ", perPage=" + perPage +
                ", totalPage=" + totalPage +
                ", prescriptionList=" + prescriptionList +
                '}';
    }

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
    @XmlElement(name="prescriptions")
    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
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

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
