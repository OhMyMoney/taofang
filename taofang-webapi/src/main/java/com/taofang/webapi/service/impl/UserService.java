package com.taofang.webapi.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.dao.MemberMapper;
import com.taofang.webapi.dao.persistence.RedisDao;
import com.taofang.webapi.domain.User;
import com.taofang.webapi.domain.ViewHistory;
import com.taofang.webapi.model.Member;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.ResultUtil;
import com.taofang.webapi.util.UserModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                System.out.println(userName);
                List<Member> members = memberMapper.selectByMemberName(userName);
                if(members == null || members.size() == 0){
                    result = ResultUtil.failResult("用户名不存在");
                }else if(!password.equals(members.get(0).getPassword())){
                    result = ResultUtil.failResult("密码与用户名不匹配");
                }
            }
            LOGGER.info("验证用户[user:" + user + "]登录条件 ==> " + result);
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
//        String smsCode = Optional.fromNullable(user.getSmsCode()).or("").trim();
        try{
            if(Strings.isNullOrEmpty(userName)){
                result = ResultUtil.failResult("用户名不能为空");
            }else if(Strings.isNullOrEmpty(password)){
                result = ResultUtil.failResult("密码不能为空");
            }else if(!password.equals(confirmPassword)){
                result = ResultUtil.failResult("两次输入的密码不匹配");
            }else if(Strings.isNullOrEmpty(phoneNumber)){
                result = ResultUtil.failResult("手机号码不能为空");
            /*else if(Strings.isNullOrEmpty(smsCode)){
                result = ResultUtil.failResult("手机验证码不能为空");
            }*/
            }else{
                /*Optional<String> redisCodeOptional = redisDao.getValueByKey("sms_code_"+phoneNumber);
                if(!redisCodeOptional.isPresent()){
                    result = ResultUtil.failResult("请重新获取验证码");
                }else if(!smsCode.equals(redisCodeOptional.get())){
                    result = ResultUtil.failResult("输入的验证码不正确");
                }else{*/
                List<Member> memberList = memberMapper.selectByMemberName(userName);
                if(memberList.size() != 0){
                    result = ResultUtil.failResult("用户名已经存在,请重新输入");
                }else{
                    Integer lastMemberId = memberMapper.selectLastMemberId();
                    Member member = UserModelUtil.tranUserToMember(user);
                    member.setMemberid(Optional.fromNullable(lastMemberId).or(0) + 1);
                    memberMapper.insertMember(member);
                }
            }
            LOGGER.info("验证用户[user:" + user + "]注册条件 ==> " + result);
        }catch(Exception e){
            LOGGER.error("验证用户[user:" + user + "]注册条件 ==> error ==> " + e.getMessage(), e);
        }

        return result;
    }

    @Override
    public List<ViewHistory> getUserViewHistoryByUserName(String userName) {
        List<ViewHistory> viewHistoryList;
        try{
            viewHistoryList = new ArrayList<>();
            ViewHistory viewHistory1 = new ViewHistory();
            viewHistory1.setId(10037);
            viewHistory1.setTypeId(1);
            viewHistory1.setTypeName("文章");
            viewHistory1.setTitle("自然疗法让我不再受疾病的折磨");
            viewHistory1.setViewDate("2016-05-01");
            viewHistory1.setViewWeek("星期日");

            ViewHistory viewHistory2 = new ViewHistory();
            viewHistory2.setId(10086);
            viewHistory2.setTypeId(2);
            viewHistory2.setTypeName("偏方");
            viewHistory2.setTitle("看八十岁老人如何玩转人生");
            viewHistory2.setViewDate("2016-05-02");
            viewHistory2.setViewWeek("星期一");

            viewHistoryList.add(viewHistory1);
            viewHistoryList.add(viewHistory2);
            LOGGER.info("根据用户名[" + userName + "]查询用户的浏览历史 ==> " + viewHistoryList);
        }catch(Exception e){
            viewHistoryList = new ArrayList<>();
            LOGGER.error("根据用户名[" + userName + "]查询用户的浏览历史 ==> error ==> " + e.getMessage(), e);
        }
        return viewHistoryList;
    }

}
