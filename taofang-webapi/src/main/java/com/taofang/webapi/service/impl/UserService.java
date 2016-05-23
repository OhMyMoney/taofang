package com.taofang.webapi.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.UserArticleBean;
import com.taofang.webapi.dao.CommentstarMapper;
import com.taofang.webapi.dao.InquiryprescriptionMapper;
import com.taofang.webapi.dao.MemberMapper;
import com.taofang.webapi.dao.PrescriptionMapper;
import com.taofang.webapi.domain.*;
import com.taofang.webapi.model.CommentstarWithBLOBs;
import com.taofang.webapi.model.Member;
import com.taofang.webapi.result.Result;
import com.taofang.webapi.service.IUserService;
import com.taofang.webapi.util.CommentModelUtil;
import com.taofang.webapi.util.MD5Util;
import com.taofang.webapi.util.ResultUtil;
import com.taofang.webapi.util.UserModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-10
 */
@Service
public class UserService implements IUserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private ObjectMapper mapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private InquiryprescriptionMapper inquiryprescriptionMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private CommentstarMapper commentstarMapper;

    @Override
    public UserDomain getUserDomainById(int userId) {
        UserDomain userDomain = new UserDomain();
        try{
            List<Member> memberList = memberMapper.selectByMemberId(userId);
            if(memberList.size() > 0){
                userDomain = UserModelUtil.tranMemberAsUserDomain(memberList.get(0));
            }
        }catch(Exception e){
            userDomain.setUserId(0);
            LOGGER.error(e.getMessage(), e);
        }
        return userDomain;
    }

    @Override
    public UserDetailDomain getUserDetailDomainById(int userId, String module) {
        UserDetailDomain userDetail;
        List<UserModuleDomain> userModuleList;
        try{
            if(module.equals("qiufang")){
                userModuleList = new ArrayList<>();
            }else if(module.equals("xianfang")){
                userModuleList = new ArrayList<>();
            }else if(module.equals("shoucang")){
                userModuleList = new ArrayList<>();
            }else if(module.equals("pinglun")){
                List<CommentstarWithBLOBs> commentstarList = commentstarMapper.selectCommentByMemberId(userId);
                userModuleList = CommentModelUtil.tranCommentstarWithBLOBListAsPinglun(commentstarList);
            }else{
                userModuleList = new ArrayList<>();
            }
            userDetail = new UserDetailDomain(userId, module, userModuleList);
        }catch(Exception e){
            userDetail = new UserDetailDomain(userId, module, new ArrayList<>());
            LOGGER.error(e.getMessage(), e);
        }
        return userDetail;
    }

    @Override
    public UserViewDomain getUserViewDomainById(int userId) {
        List<ViewDomain> viewDomainList = new ArrayList<>();
        String viewKey = "userId:" + userId + ":view";
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> viewSet = jedis.zrevrange(viewKey, 0, 19);
            for(String view : viewSet){
                viewDomainList.add(UserModelUtil.tranUserArticleBeanAsViewDomain(mapper.readValue(view, UserArticleBean.class)));
            }
        }catch(Exception e){
            viewDomainList = new ArrayList<>();
            LOGGER.error(e.getMessage(), e);
        }
        return new UserViewDomain(userId, viewDomainList);
    }

    @Override
    public boolean updateUserView(UserClickDomain userClick) {
        boolean isUpdated;
        String viewKey = "userId:" + userClick.getUserId() + ":view";
        UserArticleBean userArticle = UserModelUtil.tranUserClickDomainAsUserArticleBean(userClick);
        userArticle.setDateTime(System.currentTimeMillis());
        try(Jedis jedis = jedisPool.getResource()){
            jedis.zadd(viewKey, userArticle.getDateTime(), mapper.writeValueAsString(userArticle));
            long viewCount = jedis.zcard(viewKey);
            if(viewCount > 50){
                jedis.zremrangeByRank(viewKey, 0, viewCount - 50);
            }
            isUpdated = true;
        }catch(Exception e){
            isUpdated = false;
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserCollect(UserClickDomain userClick) {
        boolean isUpdated;
        String collectKey = "userId:" + userClick.getUserId() + ":collect";
        UserArticleBean userArticle = UserModelUtil.tranUserClickDomainAsUserArticleBean(userClick);
        try(Jedis jedis = jedisPool.getResource()){
            jedis.zadd(collectKey, System.currentTimeMillis(), mapper.writeValueAsString(userArticle));
            isUpdated = true;
        }catch(Exception e){
            isUpdated = false;
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated;
    }

    @Override
    public Result checkUserLogin(UserDomain user) {
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
                }else{
                    String memberPassword = members.get(0).getPassword();
                    if(!MD5Util.GetMD5Code(password).equals(memberPassword)){
                        result = ResultUtil.failResult("密码与用户名不匹配");
                    }else{
                        result.setData(members.get(0).getMemberid() + "");
                    }
                }
            }
            LOGGER.info("验证用户[user:" + user + "]登录条件 ==> " + result);
        }catch(Exception e){
            LOGGER.error("验证用户[user:" + user + "]登录条件 ==> error ==> " + e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Result checkUserRegister(UserDomain user) {
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
                    Member member = UserModelUtil.tranUserDomainAsMember(user);
                    member.setMemberid(Optional.fromNullable(lastMemberId).or(0) + 1);
                    memberMapper.insertMember(member);
                    result.setData((Optional.fromNullable(lastMemberId).or(0) + 1) + "");
                }
            }
            LOGGER.info("验证用户[user:" + user + "]注册条件 ==> " + result);
        }catch(Exception e){
            LOGGER.error("验证用户[user:" + user + "]注册条件 ==> error ==> " + e.getMessage(), e);
        }

        return result;
    }

    @PostConstruct
    public void init(){
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
