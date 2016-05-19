package com.taofang.webapi.service.impl;

import com.google.common.base.Strings;
import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.domain.HealthVoice;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.service.IHealthVoiceService;
import com.taofang.webapi.util.DatetimeUtil;
import com.taofang.webapi.util.HealthVoiceModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
@Service
public class HealthVoiceService implements IHealthVoiceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthVoiceService.class);
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int getHealthVoiceAmountByDate(String dateStr) {
        int count = 0;
        try{
            Timestamp videoDate = null;
            if(!Strings.isNullOrEmpty(dateStr)){
                videoDate = DatetimeUtil.tranDate(dateStr, DatetimeUtil.FORMAT_DEFAULT_YMD_MIN);
            }
            count = articleMapper.countHealthVoiceByVideoDate(videoDate);
            LOGGER.info("查询日期[" + (dateStr == null ? "全部" : dateStr) + "]的健康之声数量 ==> " + count);
        }catch(Exception e){
            LOGGER.error("查询日期[" + (dateStr == null ? "全部" : dateStr) + "]的健康之声数量 ==> error ==> " + e.getMessage(), e);
        }

        return count;
    }

    @Override
    public List<HealthVoice> getHealthVoiceByPagination(String dateStr, int start, int limit) {
        List<HealthVoice> healthVoiceList;
        try{
            Timestamp videoDate = null;
            if(!Strings.isNullOrEmpty(dateStr)){
                videoDate = DatetimeUtil.tranDate(dateStr, DatetimeUtil.FORMAT_DEFAULT_YMD_MIN);
            }
            List<ArticleWithBLOBs> articleList = articleMapper.selectHealthVoiceByVideoDate(videoDate, start, limit);
            healthVoiceList = HealthVoiceModelUtil.tranArticleList(articleList);
            LOGGER.info("查询健康之声分页[日期" + (dateStr == null ? "全部" : dateStr) + ";start:" + start + ";limit:" + limit + "]的信息 ==> " + healthVoiceList);
        }catch(Exception e){
            LOGGER.error("查询健康之声分页[日期" + (dateStr == null ? "全部" : dateStr) + ";start:" + start + ";limit:" + limit + "]的信息 ==> error ==> "
                    + e.getMessage(), e);
            healthVoiceList = new ArrayList<>();
        }

        return healthVoiceList;
    }
}
