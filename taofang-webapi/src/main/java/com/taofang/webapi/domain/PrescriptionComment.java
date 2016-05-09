package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
@XmlRootElement(name="comment")
public class PrescriptionComment {
    private String  memberName;
    private String experience;
    private String createdDate;
    private String  icon;
    private Double score;
    @XmlElement(name="memberName")
    public String getMemberName() {
        return memberName;
    }
    @XmlElement(name="experience")
    public String getExperience() {
        return experience;
    }
    @XmlElement(name="createdDate")
    public String getCreatedDate() {
        return createdDate;
    }
    @XmlElement(name="icon")
    public String getIcon() {
        return icon;
    }
    @XmlElement(name="score")
    public Double getScore() {
        return score;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
