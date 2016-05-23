package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="userClick")
public class UserClickDomain {
    private int userId;

    private int viewModuleId;

    private String viewModuleName;

    private int clickId;

    private String clickTitle;

    @XmlElement(name="userId")
    public int getUserId() {
        return userId;
    }
    @XmlElement(name="viewModuleId")
    public int getViewModuleId() {
        return viewModuleId;
    }
    @XmlElement(name="viewModuleName")
    public String getViewModuleName() {
        return viewModuleName;
    }
    @XmlElement(name="clickId")
    public int getClickId() {
        return clickId;
    }
    @XmlElement(name="clickTitle")
    public String getClickTitle() {
        return clickTitle;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setViewModuleId(int viewModuleId) {
        this.viewModuleId = viewModuleId;
    }

    public void setViewModuleName(String viewModuleName) {
        this.viewModuleName = viewModuleName;
    }

    public void setClickId(int clickId) {
        this.clickId = clickId;
    }

    public void setClickTitle(String clickTitle) {
        this.clickTitle = clickTitle;
    }
}
