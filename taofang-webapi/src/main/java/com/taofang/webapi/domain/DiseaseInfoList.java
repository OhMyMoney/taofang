package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
@XmlRootElement(name="diseaseInfoList")
public class DiseaseInfoList {
    private List<DiseaseInfo> diseaseInfoList;

    public DiseaseInfoList() {
    }

    public DiseaseInfoList(List<DiseaseInfo> diseaseInfoList) {
        this.diseaseInfoList = diseaseInfoList;
    }

    @XmlElement(name="diseaseList")
    public List<DiseaseInfo> getDiseaseInfoList() {
        return diseaseInfoList;
    }

    public void setDiseaseInfoList(List<DiseaseInfo> diseaseInfoList) {
        this.diseaseInfoList = diseaseInfoList;
    }
}
