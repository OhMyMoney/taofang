package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescription")
public class Prescription {
    // 偏方Id
    private int id;
    // 偏方标题
    private String title;
    // 偏方评分
    private double score;

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                '}';
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }

    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }

    @XmlElement(name="score")
    public double getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
