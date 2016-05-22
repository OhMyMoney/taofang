package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="userDetail")
public class UserDetailDomain {
    private int userId;

    private String moduleName;

    private List<UserModuleDomain> userModuleList;

    public UserDetailDomain() {
    }

    public UserDetailDomain(int userId, String moduleName) {
        this.userId = userId;
        this.moduleName = moduleName;
    }

    public UserDetailDomain(int userId, String moduleName, List<UserModuleDomain> userModuleList) {
        this.userId = userId;
        this.moduleName = moduleName;
        this.userModuleList = userModuleList;
    }

    @XmlElement(name="userId")
    public int getUserId() {
        return userId;
    }
    @XmlElement(name="moduleName")
    public String getModuleName() {
        return moduleName;
    }
    @XmlElement(name="userModuleList")
    public List<UserModuleDomain> getUserModuleList() {
        return userModuleList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setUserModuleList(List<UserModuleDomain> userModuleList) {
        this.userModuleList = userModuleList;
    }
}
