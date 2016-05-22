package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="articleDetail")
public class ArticleDetailDomain {
    private int articleId;

    private String articleTitle;

    private String articleContent;

    private String imageUrl;

    private String videoUrl;

    private int thumbCount;

    private String category;

    private List<RelationlinkDomain> relationlinkList;

    public ArticleDetailDomain(int articleId) {
        this.articleId = articleId;
    }

    public ArticleDetailDomain() {
    }

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
    @XmlElement(name="articleContent")
    public String getArticleContent() {
        return articleContent;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="thumbCount")
    public int getThumbCount() {
        return thumbCount;
    }
    @XmlElement(name="relationlinkList")
    public List<RelationlinkDomain> getRelationlinkList() {
        return relationlinkList;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setThumbCount(int thumbCount) {
        this.thumbCount = thumbCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRelationlinkList(List<RelationlinkDomain> relationlinkList) {
        this.relationlinkList = relationlinkList;
    }
}
