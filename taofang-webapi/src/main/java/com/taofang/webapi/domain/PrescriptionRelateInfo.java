package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescriptionRelateInfo")
public class PrescriptionRelateInfo {
    // 相关推荐
    private List<RelateInfo> recommends;
    // 自然疗法
    private List<RelateInfo> naturalRemedies;
    // 产品推荐
    private List<RelateInfo> products;
    // 食疗
    private List<RelateInfo> foodTherapies;

    @XmlElement(name="recommends")
    public List<RelateInfo> getRecommends() {
        return recommends;
    }

    @XmlElement(name="naturalRemedies")
    public List<RelateInfo> getNaturalRemedies() {
        return naturalRemedies;
    }

    @XmlElement(name="products")
    public List<RelateInfo> getProducts() {
        return products;
    }

    @XmlElement(name="foodTherapies")
    public List<RelateInfo> getFoodTherapies() {
        return foodTherapies;
    }

    public void setRecommends(List<RelateInfo> recommends) {
        this.recommends = recommends;
    }

    public void setNaturalRemedies(List<RelateInfo> naturalRemedies) {
        this.naturalRemedies = naturalRemedies;
    }

    public void setProducts(List<RelateInfo> products) {
        this.products = products;
    }

    public void setFoodTherapies(List<RelateInfo> foodTherapies) {
        this.foodTherapies = foodTherapies;
    }
}
