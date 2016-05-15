package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.UserViewBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.ModuleInfo;
import com.taofang.webapi.domain.User;
import com.taofang.webapi.domain.ViewHistory;
import com.taofang.webapi.model.CommentstarWithBLOBs;
import com.taofang.webapi.model.Inquiryprescription;
import com.taofang.webapi.model.Member;
import com.taofang.webapi.model.Prescription;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class UserModelUtil {

    public static Member tranUserToMember(User user){
        Member member = new Member();
        member.setMembername(Optional.fromNullable(user.getUserName()).or("").trim());
        member.setPassword(MD5Util.GetMD5Code((Optional.fromNullable(user.getPassword()).or("").trim())));

        return member;
    }

    public static User tranMember(Member member){
        User user = new User();
        user.setUserName(Optional.fromNullable(member.getMembername()).or(""));
        if(Strings.isNullOrEmpty(member.getIcon())){
            user.setIcon(ImageConstant.DEFAULT_HEAD_PORTRAIT_URL);
        }else{
            user.setIcon(ImageConstant.HEAD_PORTRAIT_URL + member.getIcon());
        }
        return user;
    }

    public static ModuleInfo tranInquiryprescription(Inquiryprescription inquiryprescription){
        ModuleInfo moduleInfo = new ModuleInfo();
        if(inquiryprescription.getInquiryid() == null){
            moduleInfo.setId(0);
        }
        if(!Strings.isNullOrEmpty(inquiryprescription.getTitle())){
            moduleInfo.setTitle(inquiryprescription.getTitle());
        }
        if(inquiryprescription.getCreateddate() != null){
            moduleInfo.setDateTime(
                    DateTimeUtil.tranTimestamp(
                            Timestamp.valueOf(new SimpleDateFormat(DateTimeUtil.FORMAT_DEFAULT).format(inquiryprescription.getCreateddate())),
                            DateTimeUtil.FORMAT_DEFAULT
                    ));
        }
        moduleInfo.setUrlLink("");
        moduleInfo.setModuleName("qiufang");
        return moduleInfo;
    }

    public static ModuleInfo tranPrescription(Prescription prescription){
        ModuleInfo moduleInfo = new ModuleInfo();
        moduleInfo.setId(Optional.fromNullable(prescription.getPrescriptionid()).or(0));
        moduleInfo.setTitle(Optional.fromNullable(prescription.getImageurl()).or(""));
        if(prescription.getCreatedate() != null){
            moduleInfo.setDateTime(
                    DateTimeUtil.tranTimestamp(
                            Timestamp.valueOf(new SimpleDateFormat(DateTimeUtil.FORMAT_DEFAULT).format(prescription.getCreatedate())),
                            DateTimeUtil.FORMAT_DEFAULT
                    ));
        }
        moduleInfo.setUrlLink("prescription/detail.html?id=" + moduleInfo.getId());
        moduleInfo.setModuleName("xianfang");
        return moduleInfo;
    }

    public static ModuleInfo tranCommentstarWithBLOBs(CommentstarWithBLOBs commentstar){
        ModuleInfo moduleInfo = new ModuleInfo();
        if(commentstar.getCommentid() == null){
            moduleInfo.setId(0);
        }
        moduleInfo.setTitle(Optional.fromNullable(commentstar.getExperience()).or(""));
        if(commentstar.getCreateddate() != null){
            moduleInfo.setDateTime(
                    DateTimeUtil.tranTimestamp(
                            Timestamp.valueOf(new SimpleDateFormat(DateTimeUtil.FORMAT_DEFAULT).format(commentstar.getCreateddate())),
                            DateTimeUtil.FORMAT_DEFAULT
                    ));
        }
        moduleInfo.setUrlLink("");
        moduleInfo.setModuleName("pinglun");
        return moduleInfo;
    }

    public static ViewHistory tranUserViewBean(UserViewBean userViewBean){
        ViewHistory viewHistory = new ViewHistory();
        viewHistory.setId(Optional.fromNullable(userViewBean.getViewId()).or(0));
        viewHistory.setTitle(Optional.fromNullable(userViewBean.getViewTitle()).or(""));
        long times = Optional.fromNullable(userViewBean.getTime()).or(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        viewHistory.setViewYear(calendar.get(Calendar.YEAR) + "");
        viewHistory.setViewMonth((calendar.get(Calendar.MONTH) + 1) + "");
        viewHistory.setViewDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
        viewHistory.setViewWeek("周" + getChinaWorldByInt(calendar.get(Calendar.DAY_OF_WEEK)));
        String viewType = userViewBean.getViewType();
        if(!Strings.isNullOrEmpty(viewType) && viewType.equals("article")){
            viewHistory.setTypeName("文章");
            viewHistory.setLinkUrl(userViewBean.getViewSubType() + "/detail.html?id=" + viewHistory.getId());
        }else{
            viewHistory.setTypeName("偏方");
            viewHistory.setLinkUrl("prescription/detail.html?id=" + viewHistory.getId());
        }

        return viewHistory;
    }

    private static String getChinaWorldByInt(int i){
        switch (i){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
        }
        return "日";
    }
}
