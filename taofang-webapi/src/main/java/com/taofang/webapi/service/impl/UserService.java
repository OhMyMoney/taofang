package com.taofang.webapi.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.dao.MemberMapper;
import com.taofang.webapi.dao.RedisDao;
import com.taofang.webapi.domain.User;
import com.taofang.webapi.model.Member;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.ResultUtil;
import com.taofang.webapi.util.UserModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-10
 */
@Service
public class UserService implements IUserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private RedisDao redisDao;

    @Override
    public Result checkUserLogin(User user) {
        String userName = Optional.fromNullable(user.getUserName()).or("").trim();
        String password = Optional.fromNullable(user.getPassword()).or("").trim();
        Result result = ResultUtil.successResult();
        try{
            if(Strings.isNullOrEmpty(userName)){
                result = ResultUtil.failResult("用户名不能为空");
            }else if(Strings.isNullOrEmpty(password)){
                result = ResultUtil.failResult("密码不能为空");
            }else{
                List<Member> members = memberMapper.selectByMemberName(userName);
                if(members == null || members.size() == 0){
                    result = ResultUtil.failResult("用户名不存在");
                }else if(!password.equals(members.get(0).getPassword())){
                    result = ResultUtil.failResult("密码与用户名不匹配");
                }
            }

        }catch(Exception e){
            LOGGER.error("验证用户[user:" + user + "]登录条件 ==> error ==> " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Result checkUserRegister(User user) {
        Result result = ResultUtil.successResult();
        String userName = Optional.fromNullable(user.getUserName()).or("").trim();
        String password = Optional.fromNullable(user.getPassword()).or("").trim();
        String confirmPassword = Optional.fromNullable(user.getConfirmPassword()).or("").trim();
        String phoneNumber = Optional.fromNullable(user.getPhoneNumber()).or("").trim();
        String smsCode = Optional.fromNullable(user.getSmsCode()).or("").trim();
        try{
            if(Strings.isNullOrEmpty(userName)){
                result = ResultUtil.failResult("用户名不能为空");
            }else if(Strings.isNullOrEmpty(password)){
                result = ResultUtil.failResult("密码不能为空");
            }else if(!password.equals(confirmPassword)){
                result = ResultUtil.failResult("两次输入的密码不匹配");
            }else if(Strings.isNullOrEmpty(phoneNumber)){
                result = ResultUtil.failResult("手机号码不能为空");
            }else if(Strings.isNullOrEmpty(smsCode)){
                result = ResultUtil.failResult("手机验证码不能为空");
            }else{
                Optional<String> redisCodeOptional = redisDao.getValueByKey("sms_code_"+phoneNumber);
                if(!redisCodeOptional.isPresent()){
                    result = ResultUtil.failResult("请重新获取验证码");
                }else if(!smsCode.equals(redisCodeOptional.get())){
                    result = ResultUtil.failResult("输入的验证码不正确");
                }else{
                    List<Member> memberList = memberMapper.selectByMemberName(userName);
                    if(memberList.size() != 0){
                        result = ResultUtil.failResult("用户名已经存在,请重新输入");
                    }else{
                        Member member = UserModelUtil.tranUserToMember(user);
                        memberMapper.insertMember(member);
                    }
                }
            }
        }catch(Exception e){
            LOGGER.error("验证用户[user:" + user + "]注册条件 ==> error ==> " + e.getMessage(), e);
        }

        return result;
    }

}
