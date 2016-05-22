package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="userView")
public class UserViewDomain {

    private int userId;

    private List<ViewDomain> viewList;

    public UserViewDomain(int userId, List<ViewDomain> viewList) {
        this.userId = userId;
        this.viewList = viewList;
    }
    @XmlElement(name="userId")
    public int getUserId() {
        return userId;
    }
    @XmlElement(name="viewList")
    public List<ViewDomain> getViewList() {
        return viewList;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setViewList(List<ViewDomain> viewList) {
        this.viewList = viewList;
    }
}
