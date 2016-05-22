package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.CommentBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.CommentDomain;
import com.taofang.webapi.domain.UserModuleDomain;
import com.taofang.webapi.model.CommentstarWithBLOBs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-21
 */
public class CommentModelUtil {

    public static CommentDomain tranCommentBeanAsDomain(CommentBean commentBean){
        CommentDomain commentDomain = new CommentDomain();
        commentDomain.setCommentId(Optional.fromNullable(commentBean.getCommentID()).or(0));
        commentDomain.setMemberName(Optional.fromNullable(commentBean.getMemberName()).or("匿名"));
        if(Strings.isNullOrEmpty(commentBean.getIcon())){
            commentDomain.setMemberIcon(ImageConstant.DEFAULT_HEAD_PORTRAIT_URL);
        }else{
            commentDomain.setMemberIcon(ImageConstant.HEAD_PORTRAIT_URL + commentBean.getIcon());
        }
        commentDomain.setCommentContent(Optional.fromNullable(commentBean.getExperience()).or(""));
        if(commentBean.getCreatedDate() != null){
            commentDomain.setCommentTime(DatetimeUtil.tranTimestamp(commentBean.getCreatedDate(), DatetimeUtil.FORMAT_DEFAULT));
        }
        commentDomain.setScore(Optional.fromNullable(commentBean.getTotalScore()).or(0.0) / 5.0);

        return commentDomain;
    }

    public static List<CommentDomain> tranCommentBeanListAsDomain(List<CommentBean> commentBeanList){
        List<CommentDomain> commentList = new ArrayList<>();
        for(CommentBean commentBean : commentBeanList){
            commentList.add(tranCommentBeanAsDomain(commentBean));
        }
        return commentList;
    }

    public static UserModuleDomain tranCommentstarWithBLOBsAsPinglun(CommentstarWithBLOBs commentstar){
        UserModuleDomain userModule = new UserModuleDomain();
        if(commentstar.getCommentid() == null){
            userModule.setModuleId(0);
        }
        userModule.setModuleTitle(Optional.fromNullable(commentstar.getExperience()).or(""));
        userModule.setModuleName("pinglun");
        if(commentstar.getCreateddate() != null){
            userModule.setDateTime(
                    DatetimeUtil.tranTimestamp(
                            Timestamp.valueOf(new SimpleDateFormat(DatetimeUtil.FORMAT_DEFAULT).format(commentstar.getCreateddate())),
                            DatetimeUtil.FORMAT_DEFAULT
                    ));
        }
        userModule.setUrlLink("");
        return userModule;
    }

    public static List<UserModuleDomain> tranCommentstarWithBLOBListAsPinglun(List<CommentstarWithBLOBs> commentstarList){
        List<UserModuleDomain> userModuleList = new ArrayList<>();
        for(CommentstarWithBLOBs commentstar : commentstarList){
            userModuleList.add(tranCommentstarWithBLOBsAsPinglun(commentstar));
        }
        return userModuleList;
    }
}
