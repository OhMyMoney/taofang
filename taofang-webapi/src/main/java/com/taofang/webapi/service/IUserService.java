package com.taofang.webapi.service;

import com.taofang.webapi.domain.User;
import com.taofang.webapi.domain.ViewHistory;
import com.taofang.webapi.result.Result;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-10
 */
public interface IUserService {
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

    List<ViewHistory> getUserViewHistoryByUserName(String userName);
}
