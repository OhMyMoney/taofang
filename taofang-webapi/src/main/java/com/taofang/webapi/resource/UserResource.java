package com.taofang.webapi.resource;

import com.taofang.webapi.constant.VCode;
import com.taofang.webapi.domain.UserDetailDomain;
import com.taofang.webapi.domain.UserDomain;
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

    @Path("view")
    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.TEXT_PLAIN})
    public String setUserView(String view){
        return userService.setUserView(view);
    }
}
