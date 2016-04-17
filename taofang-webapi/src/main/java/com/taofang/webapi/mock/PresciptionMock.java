package com.taofang.webapi.mock;

import com.taofang.webapi.domain.Prescription;
import com.taofang.webapi.domain.RelateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public class PresciptionMock {

    public static List<Prescription> mockPrescriptionList(int listSize){
        List<Prescription> prescriptionList = new ArrayList<>();
        for(int i=1; i<=listSize; i++){
            Prescription prescription = new Prescription();
            prescription.setId(i + 100);
            prescription.setTitle("偏方(prescription): " + i);
            prescription.setScore(i % 6);

            prescriptionList.add(prescription);
        }

        return prescriptionList;
    }

    public static List<RelateInfo> mockRelateInfo(int listSize, String relateName){
        List<RelateInfo> relateInfos = new ArrayList<>();
        for(int i=0; i<listSize; i++){
            RelateInfo relateInfo = new RelateInfo();
            relateInfo.setId(i + 10);
            relateInfo.setTitle("相关信息(" + relateName + "): " + i);

            relateInfos.add(relateInfo);
        }

        return relateInfos;
    }
}
