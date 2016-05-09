package com.taofang.webapi.bean;

import java.sql.Timestamp;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
public class PrescriptionInfoBean {
    private String ImageURL;
    private Timestamp CreateDate;
    private String CreatorName;
    private Integer PrescriptionID;
    private String Name;
    private String story;
    private String productionAndUsage;
    private String attentions;
    private String indication;

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public Integer getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(Integer prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
