package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
@XmlRootElement(name="advertisementListDomain")
public class AdvertisementListDomain {

    private String category;

    private List<AdvertisementDomain> advertisementList;

    public AdvertisementListDomain() {
    }

    public AdvertisementListDomain(String category) {
        this.category = category;
    }

    @XmlElement(name="category")
    public String getCategory() {
        return category;
    }
    @XmlElement(name="advertisementList")
    public List<AdvertisementDomain> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<AdvertisementDomain> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
