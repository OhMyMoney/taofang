package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.LiangfangBean;
import com.taofang.webapi.bean.PrescriptionInfoBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.PrescriptionDetailDomain;
import com.taofang.webapi.domain.PrescriptionDomain;
import com.taofang.webapi.domain.RelationlinkDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public class PrescriptionModelUtil {

    public static PrescriptionDomain tranLiangfangBeanAsPrescription(LiangfangBean liangfangBean){
        PrescriptionDomain prescription = new PrescriptionDomain();
        prescription.setPrescriptionId(Optional.fromNullable(liangfangBean.getP_id()).or(0));
        prescription.setPrescriptionTitle(Optional.fromNullable(liangfangBean.getP_name()).or(""));
        prescription.setPrescriptionScore(Optional.fromNullable(liangfangBean.getP_Score()).or(0.0));
        return prescription;
    }
    public static List<PrescriptionDomain> tranLiangfangBeanListAsPrescription(List<LiangfangBean> liangfangBeanList){
        List<PrescriptionDomain> prescriptionList = new ArrayList<>();

        for(LiangfangBean liangfangBean : liangfangBeanList){
            PrescriptionDomain prescription = new PrescriptionDomain();
            prescription.setPrescriptionId(Optional.fromNullable(liangfangBean.getP_id()).or(0));
            prescription.setPrescriptionTitle(Optional.fromNullable(liangfangBean.getP_name()).or(""));
            prescription.setPrescriptionScore(Optional.fromNullable(liangfangBean.getP_Score()).or(0.0));
            prescriptionList.add(prescription);
        }
        return prescriptionList;
    }

    public static RelationlinkDomain tranLiangfangBeanAsDisease(LiangfangBean liangfangBean){
        if(Strings.isNullOrEmpty(liangfangBean.getD_name())){
            return null;
        }
        String[] dNames = liangfangBean.getD_name().split(",");
        RelationlinkDomain relationlink = new RelationlinkDomain();
        relationlink.setRelationTitle(dNames[0]);
        return relationlink;
    }

    public static RelationlinkDomain tranLiangfangBeanAsSymptom(LiangfangBean liangfangBean){
        if(Strings.isNullOrEmpty(liangfangBean.getS_name())){
            return null;
        }
        String[] sNames = liangfangBean.getS_name().split(",");
        RelationlinkDomain relationlink = new RelationlinkDomain();
        relationlink.setRelationTitle(sNames[0]);
        return relationlink;
    }

    public static PrescriptionDetailDomain trantranPrescriptionInfoBeanAsDetail(PrescriptionInfoBean prescriptionInfoBean){
        PrescriptionDetailDomain prescriptionDetail = new PrescriptionDetailDomain();
        prescriptionDetail.setPrescriptionId(Optional.fromNullable(prescriptionInfoBean.getPrescriptionID()).or(0));
        prescriptionDetail.setPrescriptionTitle(Optional.fromNullable(prescriptionInfoBean.getName()).or(""));
        prescriptionDetail.setAuthor(Optional.fromNullable(prescriptionInfoBean.getCreatorName()).or(""));
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getProductionAndUsage())){
            prescriptionDetail.setProductionAndUsage(prescriptionInfoBean.getProductionAndUsage().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }else{
            prescriptionDetail.setProductionAndUsage("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getAttentions())){
            prescriptionDetail.setAttentions(prescriptionInfoBean.getAttentions().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }else{
            prescriptionDetail.setAttentions("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getIndication())){
            if(prescriptionInfoBean.getIndication().contains("<p>")){
                prescriptionDetail.setIndication(prescriptionInfoBean.getIndication().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
            }else{
                prescriptionDetail.setIndication("<p>&nbsp;&nbsp;" + prescriptionInfoBean.getIndication().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL) + "</p>");
            }
        }else{
            prescriptionDetail.setIndication("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getStory())){
            prescriptionDetail.setPrescriptionStory(prescriptionInfoBean.getStory().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }else{
            prescriptionDetail.setPrescriptionStory("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getImageURL())){
            prescriptionDetail.setImageUrl(ImageConstant.IMAGE_BASE_URL + prescriptionInfoBean.getImageURL());
        }else{
            prescriptionDetail.setImageUrl("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getCreateDateStr())){
            prescriptionDetail.setCreateTime(prescriptionInfoBean.getCreateDateStr());
        }else{
            prescriptionDetail.setCreateTime("");
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getAudio())){
            prescriptionDetail.setVideoUrl(prescriptionInfoBean.getAudio());
        }else{
            prescriptionDetail.setVideoUrl("");
        }

        return prescriptionDetail;
    }
}
