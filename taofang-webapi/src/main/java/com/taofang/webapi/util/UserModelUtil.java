package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.UserDomain;
import com.taofang.webapi.model.Member;


/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class UserModelUtil {

    public static UserDomain tranMemberAsUserDomain(Member member){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(Optional.fromNullable(member.getMemberid()).or(0));
        userDomain.setUserName(Optional.fromNullable(member.getMembername()).or(""));
        if(Strings.isNullOrEmpty(member.getIcon())){
            userDomain.setIcon(ImageConstant.DEFAULT_HEAD_PORTRAIT_URL);
        }else{
            userDomain.setIcon(ImageConstant.HEAD_PORTRAIT_URL + member.getIcon());
        }
        return userDomain;
    }

    public static Member tranUserDomainAsMember(UserDomain userDomain){
        Member member = new Member();
        member.setMembername(userDomain.getUserName());
        member.setPassword(MD5Util.GetMD5Code(userDomain.getPassword()));
        return member;
    }

    /*public static ViewHistory tranUserViewBean(UserViewBean userViewBean){
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
    }*/
}
