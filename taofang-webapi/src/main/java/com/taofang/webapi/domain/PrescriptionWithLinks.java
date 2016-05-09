package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescriptionWithLinks")
public class PrescriptionWithLinks {
    private Prescription prescription;

    private List<RelationLinkInfo> videoLinks;

    private List<RelationLinkInfo> contentLinks;

    private List<PrescriptionComment> commentList;

    public PrescriptionWithLinks() {
    }

    public PrescriptionWithLinks(Prescription prescription, List<RelationLinkInfo> videoLinks, List<RelationLinkInfo> contentLinks, List<PrescriptionComment> commentList) {
        this.prescription = prescription;
        this.videoLinks = videoLinks;
        this.contentLinks = contentLinks;
        this.commentList = commentList;
    }

    @XmlElement(name="prescription")
    public Prescription getPrescription() {
        return prescription;
    }
    @XmlElement(name="videoLinks")
    public List<RelationLinkInfo> getVideoLinks() {
        return videoLinks;
    }
    @XmlElement(name="contentLinks")
    public List<RelationLinkInfo> getContentLinks() {
        return contentLinks;
    }
    @XmlElement(name="comments")
    public List<PrescriptionComment> getCommentList() {
        return commentList;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public void setVideoLinks(List<RelationLinkInfo> videoLinks) {
        this.videoLinks = videoLinks;
    }

    public void setContentLinks(List<RelationLinkInfo> contentLinks) {
        this.contentLinks = contentLinks;
    }

    public void setCommentList(List<PrescriptionComment> commentList) {
        this.commentList = commentList;
    }
}
