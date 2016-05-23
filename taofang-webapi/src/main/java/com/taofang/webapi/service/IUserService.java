package com.taofang.webapi.service;

import com.taofang.webapi.domain.UserClickDomain;
import com.taofang.webapi.domain.UserDetailDomain;
import com.taofang.webapi.domain.UserDomain;
import com.taofang.webapi.domain.UserViewDomain;
import com.taofang.webapi.result.Result;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-10
 */
public interface IUserService {
    UserDomain getUserDomainById(int userId);

    UserDetailDomain getUserDetailDomainById(int userId, String module);

    UserViewDomain getUserViewDomainById(int userId);

    boolean updateUserView(UserClickDomain userClick);

    boolean updateUserCollect(UserClickDomain userClick);

    /**
     * 验证用户登录条件
     * @param user
     * @return
     */
    Result checkUserLogin(UserDomain user);

    /**
     * 验证用户注册条件
     * @param user
     * @return
     */
    Result checkUserRegister(UserDomain user);

}
