package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-15
 */
@XmlRootElement(name="userModuleInfo")
public class UserModuleInfo {
    private int userId;

    private String userName;

    private List<ModuleInfo> moduleInfoList;
    @XmlElement(name="userId")
    public int getUserId() {
        return userId;
    }
    @XmlElement(name="userName")
    public String getUserName() {
        return userName;
    }
    @XmlElement(name="moduleInfoList")
    public List<ModuleInfo> getModuleInfoList() {
        return moduleInfoList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setModuleInfoList(List<ModuleInfo> moduleInfoList) {
        this.moduleInfoList = moduleInfoList;
    }
}
