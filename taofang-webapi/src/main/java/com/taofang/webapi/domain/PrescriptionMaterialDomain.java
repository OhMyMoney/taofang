package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-21
 */
@XmlRootElement(name="prescriptionMaterial")
public class PrescriptionMaterialDomain {
    private int prescriptionId;

    private String material;

    public PrescriptionMaterialDomain() {
    }

    public PrescriptionMaterialDomain(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public PrescriptionMaterialDomain(int prescriptionId, String material) {
        this.prescriptionId = prescriptionId;
        this.material = material;
    }

    @XmlElement(name="prescriptionId")
    public int getPrescriptionId() {
        return prescriptionId;
    }
    @XmlElement(name="material")
    public String getMaterial() {
        return material;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
