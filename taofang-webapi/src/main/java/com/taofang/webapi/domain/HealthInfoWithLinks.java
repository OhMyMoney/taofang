package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="healthInfoWithLinks")
public class HealthInfoWithLinks {
    private HealthInfo healthInfo;

    private List<RelationLinkInfo> relationLinkInfoList;

    public HealthInfoWithLinks() {
    }

    public HealthInfoWithLinks(HealthInfo healthInfo, List<RelationLinkInfo> relationLinkInfoList) {
        this.healthInfo = healthInfo;
        this.relationLinkInfoList = relationLinkInfoList;
    }

    @XmlElement(name="healthInfo")
    public HealthInfo getHealthInfo() {
        return healthInfo;
    }
    @XmlElement(name="relationLinkInfos")
    public List<RelationLinkInfo> getRelationLinkInfoList() {
        return relationLinkInfoList;
    }

    public void setHealthInfo(HealthInfo healthInfo) {
        this.healthInfo = healthInfo;
    }

    public void setRelationLinkInfoList(List<RelationLinkInfo> relationLinkInfoList) {
        this.relationLinkInfoList = relationLinkInfoList;
    }
}
