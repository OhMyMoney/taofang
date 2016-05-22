package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-14
 */
@XmlRootElement(name="prescription")
public class PrescriptionDomain {
    private int prescriptionId;

    private String prescriptionTitle;

    private double prescriptionScore;

    public PrescriptionDomain() {
    }

    public PrescriptionDomain(int prescriptionId, String prescriptionTitle, double prescriptionScore) {
        this.prescriptionId = prescriptionId;
        this.prescriptionTitle = prescriptionTitle;
        this.prescriptionScore = prescriptionScore;
    }

    @XmlElement(name="prescriptionId")
    public int getPrescriptionId() {
        return prescriptionId;
    }
    @XmlElement(name="prescriptionTitle")
    public String getPrescriptionTitle() {
        return prescriptionTitle;
    }
    @XmlElement(name="prescriptionScore")
    public double getPrescriptionScore() {
        return prescriptionScore;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setPrescriptionTitle(String prescriptionTitle) {
        this.prescriptionTitle = prescriptionTitle;
    }

    public void setPrescriptionScore(double prescriptionScore) {
        this.prescriptionScore = prescriptionScore;
    }
}
