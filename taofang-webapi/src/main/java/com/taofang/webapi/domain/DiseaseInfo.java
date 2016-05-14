package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
@XmlRootElement(name="diseaseInfo")
public class DiseaseInfo {
    private int id;

    private String name;

    private int count;

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="name")
    public String getName() {
        return name;
    }
    @XmlElement(name="count")
    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
