package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-21
 */
@XmlRootElement(name="relationlink")
public class RelationlinkDomain {
    private int relationId;

    private String relationTitle;

    private String relationLink;

    private String relationArticleCategory;

    private int relationArticleId;

    public RelationlinkDomain() {
    }

    public RelationlinkDomain(String relationTitle) {
        this.relationTitle = relationTitle;
    }

    @XmlElement(name="relationId")
    public int getRelationId() {
        return relationId;
    }
    @XmlElement(name="relationTitle")
    public String getRelationTitle() {
        return relationTitle;
    }
    @XmlElement(name="relationLink")
    public String getRelationLink() {
        return relationLink;
    }
    @XmlElement(name="relationArticleCategory")
    public String getRelationArticleCategory() {
        return relationArticleCategory;
    }
    @XmlElement(name="relationArticleId")
    public int getRelationArticleId() {
        return relationArticleId;
    }

    public void setRelationArticleCategory(String relationArticleCategory) {
        this.relationArticleCategory = relationArticleCategory;
    }

    public void setRelationArticleId(int relationArticleId) {
        this.relationArticleId = relationArticleId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public void setRelationTitle(String relationTitle) {
        this.relationTitle = relationTitle;
    }

    public void setRelationLink(String relationLink) {
        this.relationLink = relationLink;
    }
}
