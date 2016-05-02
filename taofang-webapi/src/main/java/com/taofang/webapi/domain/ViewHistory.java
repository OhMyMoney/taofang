package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@XmlRootElement(name="viewHistory")
public class ViewHistory {
    // Id
    private int id;
    // 类型Id
    private int typeId;
    // 类型名称
    private String typeName;
    // 标题
    private String title;
    // 浏览的日期
    private String viewDate;
    // 浏览的星期
    private String viewWeek;

    @Override
    public String toString() {
        return "ViewHistory{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", title='" + title + '\'' +
                ", viewDate='" + viewDate + '\'' +
                ", viewWeek='" + viewWeek + '\'' +
                '}';
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="typeId")
    public int getTypeId() {
        return typeId;
    }
    @XmlElement(name="typeName")
    public String getTypeName() {
        return typeName;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="viewDate")
    public String getViewDate() {
        return viewDate;
    }
    @XmlElement(name="viewWeek")
    public String getViewWeek() {
        return viewWeek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

    public void setViewWeek(String viewWeek) {
        this.viewWeek = viewWeek;
    }
}
