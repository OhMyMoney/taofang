package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="relationLinkInfo")
public class RelationLinkInfo {
    private int id;
    private String title;
    private String linkUrl;
    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="linkUrl")
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
