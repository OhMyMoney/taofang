package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@XmlRootElement(name="userViewHistory")
public class UserViewHistory {
    // 用户名
    private String userName;
    // 浏览历史
    private List<ViewHistory> viewHistoryList;

    @XmlElement(name="userName")
    public String getUserName() {
        return userName;
    }
    @XmlElement(name="viewHistorys")
    public List<ViewHistory> getViewHistoryList() {
        return viewHistoryList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setViewHistoryList(List<ViewHistory> viewHistoryList) {
        this.viewHistoryList = viewHistoryList;
    }
}
