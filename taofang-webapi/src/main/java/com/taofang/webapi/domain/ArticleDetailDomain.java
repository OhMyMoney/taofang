package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@XmlRootElement(name="articleDetail")
public class ArticleDetailDomain {
    private int articleId;

    private String articleTitle;

    private String category;

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
}
