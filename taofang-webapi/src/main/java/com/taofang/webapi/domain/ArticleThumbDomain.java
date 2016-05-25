package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-25
 */
@XmlRootElement(name="articleThumb")
public class ArticleThumbDomain {
    private int articleId;

    private String articleCategory;

    private int userId;

    @XmlElement(name="articleId")
    public int getArticleId() {
        return articleId;
    }
    @XmlElement(name="articleCategory")
    public String getArticleCategory() {
        return articleCategory;
    }
    @XmlElement(name="userId")
    public int getUserId() {
        return userId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
