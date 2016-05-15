package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="relateInfo")
public class RelateInfo {
    // 相关信息id
    private int id;
    // 相关信息标题
    private String title;
    // 链接
    private String urlLink;

    public RelateInfo(String title) {
        this.title = title;
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="urlLink")
    public String getUrlLink() {
        return urlLink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
}
