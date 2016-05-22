package com.taofang.webapi.service;

import com.taofang.webapi.domain.*;
import com.taofang.webapi.result.Result;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-10
 */
public interface IUserService {
    UserDomain getUserDomainById(int userId);

    UserDetailDomain getUserDetailDomainById(int userId, String module);

    /**
     * 验证用户登录条件
     * @param user
     * @return
     */
    Result checkUserLogin(User user);

    /**
     * 验证用户注册条件
     * @param user
     * @return
     */
    Result checkUserRegister(User user);

    String setUserView(String view);

    List<ViewHistory> getUserViewHistoryByUserId(String userId);

    User getUserInfoById(String userId);

    List<ModuleInfo> getModuleInfoByUserIdAndModuleName(String userId, String moduleName);

}
