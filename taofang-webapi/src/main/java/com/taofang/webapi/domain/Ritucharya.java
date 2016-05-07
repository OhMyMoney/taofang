package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="ritucharya")
public class Ritucharya {
    private int id;

    private String title;

    private String imageUrl;

    private String summary;

    private int isdts;

    private String videoUrl;

    private String bgVideoUrl;
    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="summary")
    public String getSummary() {
        return summary;
    }
    @XmlElement(name="isdts")
    public int getIsdts() {
        return isdts;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="bgVideoUrl")
    public String getBgVideoUrl() {
        return bgVideoUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setIsdts(int isdts) {
        this.isdts = isdts;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setBgVideoUrl(String bgVideoUrl) {
        this.bgVideoUrl = bgVideoUrl;
    }
}
