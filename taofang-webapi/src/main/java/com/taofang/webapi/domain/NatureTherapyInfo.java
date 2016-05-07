package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-04
 */
@XmlRootElement(name="natureTherapy")
public class NatureTherapyInfo {
    private int id;

    private String title;

    private String content;

    private String imageUrl;

    private String videoUrl;

    private int thumbCount;

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="content")
    public String getContent() {
        return content;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="thumbCount")
    public int getThumbCount() {
        return thumbCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setThumbCount(int thumbCount) {
        this.thumbCount = thumbCount;
    }
}
