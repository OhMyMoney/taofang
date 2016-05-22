package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="prescriptionPagination")
public class PrescriptionPaginationDomain {
    private String prescription;

    private int orderId;

    private PaginationDomain pagination;

    private List<PrescriptionDomain> prescriptionList;

    private List<RelationlinkDomain> diseaselinkList;

    private List<RelationlinkDomain> symptomlinkList;

    private List<RelationlinkDomain> nataropathylinkList;

    public PrescriptionPaginationDomain() {
    }

    public PrescriptionPaginationDomain(String prescription, int orderId) {
        this.prescription = prescription;
        this.orderId = orderId;
    }

    public PrescriptionPaginationDomain(String prescription, PaginationDomain pagination, List<PrescriptionDomain> prescriptionList) {
        this.prescription = prescription;
        this.pagination = pagination;
        this.prescriptionList = prescriptionList;
    }
    @XmlElement(name="prescription")
    public String getPrescription() {
        return prescription;
    }
    @XmlElement(name="pagination")
    public PaginationDomain getPagination() {
        return pagination;
    }
    @XmlElement(name="prescriptionList")
    public List<PrescriptionDomain> getPrescriptionList() {
        return prescriptionList;
    }
    @XmlElement(name="diseaselinkList")
    public List<RelationlinkDomain> getDiseaselinkList() {
        return diseaselinkList;
    }
    @XmlElement(name="symptomlinkList")
    public List<RelationlinkDomain> getSymptomlinkList() {
        return symptomlinkList;
    }
    @XmlElement(name="nataropathylinkList")
    public List<RelationlinkDomain> getNataropathylinkList() {
        return nataropathylinkList;
    }
    @XmlElement(name="orderId")
    public int getOrderId() {
        return orderId;
    }

    public void setPagination(PaginationDomain pagination) {
        this.pagination = pagination;
    }

    public void setPrescriptionList(List<PrescriptionDomain> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setDiseaselinkList(List<RelationlinkDomain> diseaselinkList) {
        this.diseaselinkList = diseaselinkList;
    }

    public void setSymptomlinkList(List<RelationlinkDomain> symptomlinkList) {
        this.symptomlinkList = symptomlinkList;
    }

    public void setNataropathylinkList(List<RelationlinkDomain> nataropathylinkList) {
        this.nataropathylinkList = nataropathylinkList;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
