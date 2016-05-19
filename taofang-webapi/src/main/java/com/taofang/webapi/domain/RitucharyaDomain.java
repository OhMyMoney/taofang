package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="ritucharya")
public class RitucharyaDomain {
    private int ritucharyaId;

    private String ritucharyaTitle;

    private String category;

    private String ritucharya;

    private String videoUrl;

    private String imageUrl;

    @XmlElement(name="ritucharyaId")
    public int getRitucharyaId() {
        return ritucharyaId;
    }
    @XmlElement(name="ritucharyaTitle")
    public String getRitucharyaTitle() {
        return ritucharyaTitle;
    }
    @XmlElement(name="category")
    public String getCategory() {
        return category;
    }
    @XmlElement(name="ritucharya")
    public String getRitucharya() {
        return ritucharya;
    }
    @XmlElement(name="videoUrl")
    public String getVideoUrl() {
        return videoUrl;
    }
    @XmlElement(name="imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setRitucharyaId(int ritucharyaId) {
        this.ritucharyaId = ritucharyaId;
    }

    public void setRitucharyaTitle(String ritucharyaTitle) {
        this.ritucharyaTitle = ritucharyaTitle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRitucharya(String ritucharya) {
        this.ritucharya = ritucharya;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
