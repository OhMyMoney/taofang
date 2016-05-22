package com.taofang.webapi.domain;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
public class UserClickDomain {
    private int userId;

    private int viewModuleId;

    private String viewModuleName;

    private long clickTime;

    private int clickId;

    private int clickTitle;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getViewModuleId() {
        return viewModuleId;
    }

    public void setViewModuleId(int viewModuleId) {
        this.viewModuleId = viewModuleId;
    }

    public String getViewModuleName() {
        return viewModuleName;
    }

    public void setViewModuleName(String viewModuleName) {
        this.viewModuleName = viewModuleName;
    }

    public long getClickTime() {
        return clickTime;
    }

    public void setClickTime(long clickTime) {
        this.clickTime = clickTime;
    }

    public int getClickId() {
        return clickId;
    }

    public void setClickId(int clickId) {
        this.clickId = clickId;
    }

    public int getClickTitle() {
        return clickTitle;
    }

    public void setClickTitle(int clickTitle) {
        this.clickTitle = clickTitle;
    }
}
