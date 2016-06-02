package com.taofang.webapi.bean;

import java.sql.Date;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
public class PrescriptionInfoBean {
    private String imageURL;
    private Date createDate;
    private String createDateStr;
    private String creatorName;
    private Integer prescriptionID;
    private String name;
    private String story;
    private String productionAndUsage;
    private String attentions;
    private String indication;
    private String audio;

    @Override
    public String toString() {
        return "PrescriptionInfoBean{" +
                "imageURL='" + imageURL + '\'' +
                ", createDate=" + createDate +
                ", createDateStr='" + createDateStr + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", prescriptionID=" + prescriptionID +
                ", name='" + name + '\'' +
                ", story='" + story + '\'' +
                ", productionAndUsage='" + productionAndUsage + '\'' +
                ", attentions='" + attentions + '\'' +
                ", indication='" + indication + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(Integer prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getProductionAndUsage() {
        return productionAndUsage;
    }

    public void setProductionAndUsage(String productionAndUsage) {
        this.productionAndUsage = productionAndUsage;
    }

    public String getAttentions() {
        return attentions;
    }

    public void setAttentions(String attentions) {
        this.attentions = attentions;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
