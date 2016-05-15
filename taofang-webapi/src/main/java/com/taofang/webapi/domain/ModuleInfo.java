package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-15
 */
@XmlRootElement(name="moduleInfo")
public class ModuleInfo {
    private int id;

    private String title;

    private String dateTime;

    private String urlLink;

    private String moduleName;
    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="dateTime")
    public String getDateTime() {
        return dateTime;
    }
    @XmlElement(name="urlLink")
    public String getUrlLink() {
        return urlLink;
    }
    @XmlElement(name="moduleName")
    public String getModuleName() {
        return moduleName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
