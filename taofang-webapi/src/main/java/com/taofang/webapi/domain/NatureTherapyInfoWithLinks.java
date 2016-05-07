package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-04
 */
@XmlRootElement(name="natureTherapyInfoWithLinks")
public class NatureTherapyInfoWithLinks {
    private NatureTherapyInfo natureTherapyInfo;

    private List<RelationLinkInfo> relationLinkInfoList;

    public NatureTherapyInfoWithLinks() {
    }

    public NatureTherapyInfoWithLinks(NatureTherapyInfo natureTherapyInfo, List<RelationLinkInfo> relationLinkInfoList) {
        this.natureTherapyInfo = natureTherapyInfo;
        this.relationLinkInfoList = relationLinkInfoList;
    }
    @XmlElement(name="natureTherapyInfo")
    public NatureTherapyInfo getNatureTherapyInfo() {
        return natureTherapyInfo;
    }
    @XmlElement(name="relationLinkInfos")
    public List<RelationLinkInfo> getRelationLinkInfoList() {
        return relationLinkInfoList;
    }

    public void setNatureTherapyInfo(NatureTherapyInfo natureTherapyInfo) {
        this.natureTherapyInfo = natureTherapyInfo;
    }

    public void setRelationLinkInfoList(List<RelationLinkInfo> relationLinkInfoList) {
        this.relationLinkInfoList = relationLinkInfoList;
    }
}
