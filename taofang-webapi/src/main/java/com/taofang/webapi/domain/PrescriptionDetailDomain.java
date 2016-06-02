package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-21
 */
@XmlRootElement(name="prescriptionDetail")
public class PrescriptionDetailDomain {
    private int prescriptionId;

    private String prescriptionTitle;

    private String author;

    private String createTime;

    private String prescriptionStory;

    private String imageUrl;

    private String videoUrl;

    private String productionAndUsage;

    private String attentions;

    private String indication;

    private String diseaseNames;

    private int commentCount;

    private List<RelationlinkDomain> videolinkList;

    private List<RelationlinkDomain> contentlinkList;

    public PrescriptionDetailDomain() {
    }

    public PrescriptionDetailDomain(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public PrescriptionDetailDomain(int prescriptionId, String prescriptionTitle, String author, String createTime, String prescriptionStory, String imageUrl, String productionAndUsage, String attentions, String indication, String diseaseNames, int commentCount, List<RelationlinkDomain> videolinkList, List<RelationlinkDomain> contentlinkList) {
        this.prescriptionId = prescriptionId;
        this.prescriptionTitle = prescriptionTitle;
        this.author = author;
        this.createTime = createTime;
        this.prescriptionStory = prescriptionStory;
        this.imageUrl = imageUrl;
        this.productionAndUsage = productionAndUsage;
        this.attentions = attentions;
        this.indication = indication;
        this.diseaseNames = diseaseNames;
        this.commentCount = commentCount;
        this.videolinkList = videolinkList;
        this.contentlinkList = contentlinkList;
    }

    @XmlElement(name="prescriptionId")
    public int getPrescriptionId() {
        return prescriptionId;
    }
    @XmlElement(name="prescriptionTitle")
    public String getPrescriptionTitle() {
        return prescriptionTitle;
    }
    @XmlElement(name="author")
    public String getAuthor() {
        return author;
    }
    @XmlElement(name="createTime")
    public String getCreateTime() {
        return createTime;
    }
    @XmlElement(name="prescriptionStory")
    public String getPrescriptionStory() {
        return prescriptionStory;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="productionAndUsage")
    public String getProductionAndUsage() {
        return productionAndUsage;
    }
    @XmlElement(name="attentions")
    public String getAttentions() {
        return attentions;
    }
    @XmlElement(name="indication")
    public String getIndication() {
        return indication;
    }
    @XmlElement(name="diseaseNames")
    public String getDiseaseNames() {
        return diseaseNames;
    }
    @XmlElement(name="commentCount")
    public int getCommentCount() {
        return commentCount;
    }
    @XmlElement(name="videolinkList")
    public List<RelationlinkDomain> getVideolinkList() {
        return videolinkList;
    }
    @XmlElement(name="contentlinkList")
    public List<RelationlinkDomain> getContentlinkList() {
        return contentlinkList;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setPrescriptionTitle(String prescriptionTitle) {
        this.prescriptionTitle = prescriptionTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setPrescriptionStory(String prescriptionStory) {
        this.prescriptionStory = prescriptionStory;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductionAndUsage(String productionAndUsage) {
        this.productionAndUsage = productionAndUsage;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public void setDiseaseNames(String diseaseNames) {
        this.diseaseNames = diseaseNames;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setVideolinkList(List<RelationlinkDomain> videolinkList) {
        this.videolinkList = videolinkList;
    }

    public void setContentlinkList(List<RelationlinkDomain> contentlinkList) {
        this.contentlinkList = contentlinkList;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
