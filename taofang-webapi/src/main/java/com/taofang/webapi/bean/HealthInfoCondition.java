package com.taofang.webapi.bean;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public class HealthInfoCondition {
    private int id;

    private int perPage;

    private int curPage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}
