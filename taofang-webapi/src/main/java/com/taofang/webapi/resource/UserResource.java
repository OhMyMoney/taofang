package com.taofang.webapi.resource;

import com.google.common.base.Strings;
import com.taofang.webapi.constant.VCode;
import com.taofang.webapi.domain.User;
import com.taofang.webapi.domain.UserModuleInfo;
import com.taofang.webapi.domain.UserViewHistory;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-06
 */
@Path("user")
public class UserResource {
    @Autowired
    private IUserService userService;

    /**
     * 获取验证码
     * @return
     */
    @Path("vcode")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getVCode(){
        return VCode.getVCodeName();
    }

    /**
     * 用户登录
     * @param user
     */
    @Path("login")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String login(User user){
        Result result = userService.checkUserLogin(user);
        if(result.getCode() == ResultUtil.SUCCESS_CODE){
            return "ok;" + result.getData();
        }else{
            return result.getFailMessages().get(0);
        }
    }

    /**
     * 用户注册
     * @param user
     */
    @Path("register")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String register(User user){
        Result result = userService.checkUserRegister(user);
        if(result.getCode() == ResultUtil.SUCCESS_CODE){
            return "ok;" + result.getData();
        }else{
            return result.getFailMessages().get(0);
        }
    }

    @Path("view")
    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String setUserView(String view){
        return userService.setUserView(view);
    }

    @Path("viewhistory")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public UserViewHistory getUserViewHistory(@QueryParam("userId")String userId){
        UserViewHistory userViewHistory = new UserViewHistory();
        if(!Strings.isNullOrEmpty(userId)){
            userViewHistory.setUserName(userId);
            userViewHistory.setViewHistoryList(userService.getUserViewHistoryByUserId(userId));
        }
        return userViewHistory;
    }

    @Path("info")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserInfoByUserId(@QueryParam("userId")String userId){
        return userService.getUserInfoById(userId);
    }

    @Path("module")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public UserModuleInfo getUserModuleInfoByUserId(@QueryParam("userId")String userId,
                                                    @QueryParam("module")String module){
        UserModuleInfo userModuleInfo = new UserModuleInfo();
        int userIdInteger = 0;
        try{
            userIdInteger = Integer.parseInt(userId);
        }catch(Exception e){
        }
        userModuleInfo.setUserId(userIdInteger);
        userModuleInfo.setModuleInfoList(userService.getModuleInfoByUserIdAndModuleName(userId, module));
        return userModuleInfo;
    }
}
