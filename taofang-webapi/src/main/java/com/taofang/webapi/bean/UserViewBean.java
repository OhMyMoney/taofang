package com.taofang.webapi.bean;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
public class UserViewBean {
    private Integer viewId;

    private String viewTitle;

    private Long time;

    private String viewType;

    private String viewSubType;

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getViewTitle() {
        return viewTitle;
    }

    public void setViewTitle(String viewTitle) {
        this.viewTitle = viewTitle;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getViewSubType() {
        return viewSubType;
    }

    public void setViewSubType(String viewSubType) {
        this.viewSubType = viewSubType;
    }
}
