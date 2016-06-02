package com.taofang.webapi.resource;

import com.taofang.webapi.constant.VCode;
import com.taofang.webapi.domain.*;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private IUserService userService;

    @GET
    @Path("/userinfo/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public UserDomain getUserByPath(@DefaultValue("0")@PathParam("userId")int userId){
        return userService.getUserDomainById(userId);
    }

    @GET
    @Path("/detail/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public UserDetailDomain getUserDetailByPath(@DefaultValue("0")@PathParam("userId")int userId,
                                                @DefaultValue("")@QueryParam("module") String module){
        return userService.getUserDetailDomainById(userId, module);
    }

    @GET
    @Path("/view/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public UserViewDomain getUserViewDomainByPath(@DefaultValue("0")@PathParam("userId")int userId){
        return userService.getUserViewDomainById(userId);
    }

    @POST
    @Path("/view/{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String updateUserViewByPath(UserClickDomain userClick){
        if(userService.updateUserView(userClick)){
            return "success";
        }else{
            return "fail";
        }
    }

    @GET
    @Path("/sms/{phoneNumber}")
    @Produces({MediaType.TEXT_PLAIN})
    public String sendUserSMSVcodeByPath(@DefaultValue("")@PathParam("phoneNumber") String phoneNumber){
        if(userService.sendSMSVcode(phoneNumber)){
            return "success";
        }else{
            return "fail";
        }
    }

    @POST
    @Path("/wxqq")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String loginByWXQQ(UserWXQQLoginDomain userWXQQLogin){
        Integer userId = null;
        LOGGER.info(userWXQQLogin.getCode() + "; " + userWXQQLogin.getLoginType());
        if(userWXQQLogin.getLoginType().equals("weixing")){
            userId = userService.loginByWX(userWXQQLogin.getCode());
        }
        return userId == null ? "fail" : ("success;" + userId);
    }

    @POST
    @Path("/collect/{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String updateUserCollectByPath(UserClickDomain userClick){
        if(userService.updateUserCollect(userClick)){
            return "success";
        }else{
            return "fail";
        }
    }


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
    public String login(UserDomain user){
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
    public String register(UserDomain user){
        Result result = userService.checkUserRegister(user);
        if(result.getCode() == ResultUtil.SUCCESS_CODE){
            return "ok;" + result.getData();
        }else{
            return result.getFailMessages().get(0);
        }
    }
}
