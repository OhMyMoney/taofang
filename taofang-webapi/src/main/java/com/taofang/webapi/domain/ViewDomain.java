package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="view")
public class ViewDomain {
    private int articleId;

    private String articleTitle;

    private int category;

    private String categoryName;

    private String categoryDesc;

    private String viewDate;

    private String viewTime;

    private String viewDateYear;

    private String viewDateMonth;

    private String viewDateDay;

    private String viewDateWeek;

    @XmlElement(name="articleId")
    public int getArticleId() {
        return articleId;
    }
    @XmlElement(name="articleTitle")
    public String getArticleTitle() {
        return articleTitle;
    }
    @XmlElement(name="category")
    public int getCategory() {
        return category;
    }
    @XmlElement(name="categoryName")
    public String getCategoryName() {
        return categoryName;
    }
    @XmlElement(name="userName")
    public String getCategoryDesc() {
        return categoryDesc;
    }
    @XmlElement(name="viewDate")
    public String getViewDate() {
        return viewDate;
    }
    @XmlElement(name="viewTime")
    public String getViewTime() {
        return viewTime;
    }
    @XmlElement(name="viewDateYear")
    public String getViewDateYear() {
        return viewDateYear;
    }
    @XmlElement(name="viewDateMonth")
    public String getViewDateMonth() {
        return viewDateMonth;
    }
    @XmlElement(name="viewDateDay")
    public String getViewDateDay() {
        return viewDateDay;
    }
    @XmlElement(name="viewDateWeek")
    public String getViewDateWeek() {
        return viewDateWeek;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    public void setViewDateYear(String viewDateYear) {
        this.viewDateYear = viewDateYear;
    }

    public void setViewDateMonth(String viewDateMonth) {
        this.viewDateMonth = viewDateMonth;
    }

    public void setViewDateDay(String viewDateDay) {
        this.viewDateDay = viewDateDay;
    }

    public void setViewDateWeek(String viewDateWeek) {
        this.viewDateWeek = viewDateWeek;
    }
}
