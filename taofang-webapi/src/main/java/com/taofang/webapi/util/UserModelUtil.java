package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.User;
import com.taofang.webapi.model.Member;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
public class UserModelUtil {

    public static Member tranUserToMember(User user){
        Member member = new Member();
        member.setMembername(Optional.fromNullable(user.getUserName()).or("").trim());
        member.setPassword(Optional.fromNullable(user.getPassword()).or("").trim());

        return member;
    }
}
