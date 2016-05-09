package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescription")
public class Prescription {
    // 偏方Id
    private int id;
    // 偏方标题
    private String title;
    // 偏方评分
    private double score;

    private String author;

    private String createTime;

    private String imageUrl;

    private String story;

    private String productionAndUsage;

    private String attentions;

    private String indication;

    private String diseaseNames;

    private int commentCount;

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="score")
    public double getScore() {
        return score;
    }
    @XmlElement(name="author")
    public String getAuthor() {
        return author;
    }
    @XmlElement(name="createTime")
    public String getCreateTime() {
        return createTime;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="story")
    public String getStory() {
        return story;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStory(String story) {
        this.story = story;
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
}
