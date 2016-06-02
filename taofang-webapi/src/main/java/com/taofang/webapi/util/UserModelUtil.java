package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.LoginUserBean;
import com.taofang.webapi.bean.UserArticleBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.constant.TaofangModule;
import com.taofang.webapi.domain.UserClickDomain;
import com.taofang.webapi.domain.UserDomain;
import com.taofang.webapi.domain.ViewDomain;
import com.taofang.webapi.model.Member;

import java.util.Calendar;


/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class UserModelUtil {

    public static UserDomain tranMemberAsUserDomain(Member member){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(Optional.fromNullable(member.getMemberid()).or(0));
        userDomain.setUserName(Optional.fromNullable(member.getNickname()).or(""));
        if(Strings.isNullOrEmpty(member.getIcon())){
            userDomain.setIcon(ImageConstant.DEFAULT_HEAD_PORTRAIT_URL);
        }else{
            if(member.getIcon().contains("wx.qlogo.cn")){
                userDomain.setIcon(member.getIcon());
            }else{
                userDomain.setIcon(ImageConstant.HEAD_PORTRAIT_URL + member.getIcon());
            }
        }
        return userDomain;
    }

    public static Member tranUserDomainAsMember(UserDomain userDomain){
        Member member = new Member();
        member.setMembername(userDomain.getUserName());
        member.setPassword(MD5Util.GetMD5Code(userDomain.getPassword()));
        return member;
    }

    public static Member tranLoginUserBeanAsMember(LoginUserBean loginUser){
        Member member = new Member();
        member.setMembername(loginUser.getOpenid());
        member.setPassword(MD5Util.GetMD5Code("123456"));
        member.setSex(loginUser.getSex());
        member.setNickname(loginUser.getNickname());
        member.setIcon(loginUser.getHeadimgurl().replaceAll("\\/", "/"));

        return member;
    }

    public static UserArticleBean tranUserClickDomainAsUserArticleBean(UserClickDomain userClick){
        UserArticleBean userArticle = new UserArticleBean();
        userArticle.setArticleId(userClick.getClickId());
        userArticle.setArticleTitle(userClick.getClickTitle());
        userArticle.setArticleCategory(userClick.getViewModuleName());

        return userArticle;
    }

    public static ViewDomain tranUserArticleBeanAsViewDomain(UserArticleBean userArticle){
        ViewDomain viewDomain = new ViewDomain();
        viewDomain.setArticleId(userArticle.getArticleId());
        viewDomain.setArticleTitle(userArticle.getArticleTitle());
        viewDomain.setCategoryName(userArticle.getArticleCategory());
        viewDomain.setCategoryDesc(TaofangModule.getEnumByName(userArticle.getArticleCategory()).moduleDesc);
        long times = Optional.fromNullable(userArticle.getDateTime()).or(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(times);
        viewDomain.setViewDateYear(calendar.get(Calendar.YEAR) + "");
        viewDomain.setViewDateMonth((calendar.get(Calendar.MONTH) + 1) + "");
        viewDomain.setViewDateDay(calendar.get(Calendar.DAY_OF_MONTH) + "");
        viewDomain.setViewDate(viewDomain.getViewDateYear() + "-" + viewDomain.getViewDateMonth() + "-" + viewDomain.getViewDateDay());
        viewDomain.setViewTime(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        viewDomain.setViewDateWeek("周" + getChinaWordByInt(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        return viewDomain;
    }

    private static String getChinaWordByInt(int i){
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
