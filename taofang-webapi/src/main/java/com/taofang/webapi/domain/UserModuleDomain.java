package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="module")
public class UserModuleDomain {

    private String moduleName;

    private int moduleId;

    private String moduleTitle;

    private String dateTime;

    private String urlLink;
    @XmlElement(name="moduleName")
    public String getModuleName() {
        return moduleName;
    }
    @XmlElement(name="moduleId")
    public int getModuleId() {
        return moduleId;
    }
    @XmlElement(name="moduleTitle")
    public String getModuleTitle() {
        return moduleTitle;
    }
    @XmlElement(name="dateTime")
    public String getDateTime() {
        return dateTime;
    }
    @XmlElement(name="urlLink")
    public String getUrlLink() {
        return urlLink;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
}
