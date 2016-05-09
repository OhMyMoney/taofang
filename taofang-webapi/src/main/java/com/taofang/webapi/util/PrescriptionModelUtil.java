package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.CommentBean;
import com.taofang.webapi.bean.LiangfangBean;
import com.taofang.webapi.bean.PrescriptionInfoBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.Prescription;
import com.taofang.webapi.domain.PrescriptionComment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public class PrescriptionModelUtil {

    public static Prescription tranLiangfangBean(LiangfangBean liangfangBean){
        Prescription prescription = new Prescription();
        prescription.setId(Optional.fromNullable(liangfangBean.getP_id()).or(0));
        prescription.setTitle(Optional.fromNullable(liangfangBean.getP_name()).or(""));
        prescription.setScore(Optional.fromNullable(liangfangBean.getP_Score()).or(0.0));
        return prescription;
    }

    public static Prescription tranPrescriptionInfoBean(PrescriptionInfoBean prescriptionInfoBean){
        Prescription prescription = new Prescription();
        prescription.setId(Optional.fromNullable(prescriptionInfoBean.getPrescriptionID()).or(0));
        prescription.setTitle(Optional.fromNullable(prescriptionInfoBean.getName()).or(""));
        prescription.setAuthor(Optional.fromNullable(prescriptionInfoBean.getCreatorName()).or(""));
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getProductionAndUsage())){
            prescription.setProductionAndUsage(prescriptionInfoBean.getProductionAndUsage().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getAttentions())){
            prescription.setAttentions(prescriptionInfoBean.getAttentions().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getIndication())){
            prescription.setIndication(prescriptionInfoBean.getIndication().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getStory())){
            prescription.setStory(prescriptionInfoBean.getStory().replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL));
        }
        if(!Strings.isNullOrEmpty(prescriptionInfoBean.getImageURL())){
            prescription.setImageUrl(ImageConstant.IMAGE_BASE_URL + prescriptionInfoBean.getImageURL());
        }else{
            prescription.setImageUrl("");
        }
        if(prescriptionInfoBean.getCreateDate() != null){
            prescription.setCreateTime(DateTimeUtil.tranTimestamp(prescriptionInfoBean.getCreateDate(), DateTimeUtil.FORMAT_DEFAULT));
        }
        return prescription;
    }

    public static PrescriptionComment tranCommentBean(CommentBean commentBean){
        PrescriptionComment prescriptionComment = new PrescriptionComment();
        prescriptionComment.setMemberName(Optional.fromNullable(commentBean.getMemberName()).or("匿名"));
        if(Strings.isNullOrEmpty(commentBean.getIcon())){
            prescriptionComment.setIcon(ImageConstant.DEFAULT_HEAD_PORTRAIT_URL);
        }else{
            prescriptionComment.setIcon(ImageConstant.HEAD_PORTRAIT_URL + commentBean.getIcon());
        }
        prescriptionComment.setExperience(Optional.fromNullable(commentBean.getExperience()).or(""));
        prescriptionComment.setScore(Optional.fromNullable(commentBean.getTotalScore()).or(0.0) / 5.0);
        if(commentBean.getCreatedDate() != null){
            prescriptionComment.setCreatedDate(DateTimeUtil.tranTimestamp(commentBean.getCreatedDate(), DateTimeUtil.FORMAT_DEFAULT));
        }

        return prescriptionComment;
    }

    public static List<PrescriptionComment> tranCommentBeanList(List<CommentBean> commentBeanList){
        List<PrescriptionComment> commentList = new ArrayList<>();
        for(CommentBean commentBean : commentBeanList){
            commentList.add(tranCommentBean(commentBean));
        }
        return commentList;
    }
}
