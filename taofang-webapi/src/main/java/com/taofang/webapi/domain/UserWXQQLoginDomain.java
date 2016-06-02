package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-26
 */
@XmlRootElement(name="userWXQQLogin")
public class UserWXQQLoginDomain {
    private String code;

    private String loginType;

    @XmlElement(name="code")
    public String getCode() {
        return code;
    }
    @XmlElement(name="loginType")
    public String getLoginType() {
        return loginType;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
