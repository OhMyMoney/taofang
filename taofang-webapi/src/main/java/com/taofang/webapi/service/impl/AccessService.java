package com.taofang.webapi.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.taofang.webapi.bean.AccessBean;
import com.taofang.webapi.constant.AccessConstant;
import com.taofang.webapi.service.IAccessService;
import com.taofang.webapi.sms.WebClient;
import com.taofang.webapi.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-05
 */
@Service
public class AccessService implements IAccessService{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessService.class);
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String getWeixinAccessSignature() {
        String signature = "";
        try(Jedis jedis = jedisPool.getResource()){
            String wxSignature = jedis.get("WX:signature");
            if(Strings.isNullOrEmpty(wxSignature)){
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                WebClient webClient = new WebClient();
                String accessTokenJSON = webClient.doGet(AccessConstant.WX_GET_ACCESS_TOKEN_URL);
                String accessToken = mapper.readValue(accessTokenJSON, AccessBean.class).getAccess_token();
                String jsapiTicketJSON = webClient.doGet(AccessConstant.WX_GET_JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken));
                String jsapiTicket = mapper.readValue(jsapiTicketJSON, AccessBean.class).getTicket();
                String jsapiTicketSHA1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=99taofang&timestamp=1465116554000&url=http://m.99taofang.com/views/taofang.html";
                signature = MD5Util.SHA1(jsapiTicketSHA1);
                jedis.set("WX:signature", signature, "NX", "EX", 3600);
            }else{
                signature = wxSignature;
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return signature;
    }
}
