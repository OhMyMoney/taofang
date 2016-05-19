package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="article")
public class ArticleDomain {
    private int articleId;

    private String articleTitle;

    private String category;

    private String videoUrl;

    private String imageUrl;

    private String summary;

    private int ritucharya;

    @XmlElement(name="articleId")
    public int getArticleId() {
        return articleId;
    }
    @XmlElement(name="articleTitle")
    public String getArticleTitle() {
        return articleTitle;
    }
    @XmlElement(name="category")
    public String getCategory() {
        return category;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="summary")
    public String getSummary() {
        return summary;
    }
    @XmlElement(name="ritucharya")
    public int getRitucharya() {
        return ritucharya;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRitucharya(int ritucharya) {
        this.ritucharya = ritucharya;
    }
}
