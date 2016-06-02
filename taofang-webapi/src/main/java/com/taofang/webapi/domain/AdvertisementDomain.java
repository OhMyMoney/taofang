package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
@XmlRootElement(name="advertisementDomain")
public class AdvertisementDomain {
    private int adId;

    private String adLink;

    private String adImage;

    private String adTitle;

    private String adCategory;

    @XmlElement(name="adId")
    public int getAdId() {
        return adId;
    }
    @XmlElement(name="adLink")
    public String getAdLink() {
        return adLink;
    }
    @XmlElement(name="adImage")
    public String getAdImage() {
        return adImage;
    }
    @XmlElement(name="adTitle")
    public String getAdTitle() {
        return adTitle;
    }
    @XmlElement(name="adCategory")
    public String getAdCategory() {
        return adCategory;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public void setAdCategory(String adCategory) {
        this.adCategory = adCategory;
    }
}
