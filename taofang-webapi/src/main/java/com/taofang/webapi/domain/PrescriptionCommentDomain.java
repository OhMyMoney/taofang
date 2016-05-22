package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescriptionComment")
public class PrescriptionCommentDomain {
    private int prescriptionId;

    private List<CommentDomain> commentList;

    public PrescriptionCommentDomain() {
    }

    public PrescriptionCommentDomain(int prescriptionId, List<CommentDomain> commentList) {
        this.prescriptionId = prescriptionId;
        this.commentList = commentList;
    }

    @XmlElement(name="prescriptionId")
    public int getPrescriptionId() {
        return prescriptionId;
    }
    @XmlElement(name="commentList")
    public List<CommentDomain> getCommentList() {
        return commentList;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setCommentList(List<CommentDomain> commentList) {
        this.commentList = commentList;
    }
}
