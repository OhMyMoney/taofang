package com.taofang.webapi.resource;

import com.taofang.webapi.domain.User;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
            return "OK";
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
            return "OK";
        }else{
            return result.getFailMessages().get(0);
        }
    }
}
