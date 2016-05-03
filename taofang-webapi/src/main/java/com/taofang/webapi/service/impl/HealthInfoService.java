package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.domain.HealthInfo;
import com.taofang.webapi.model.Article;
import com.taofang.webapi.service.IHealthInfoService;
import com.taofang.webapi.util.HealthInfoModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
@Service
public class HealthInfoService implements IHealthInfoService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthInfoService.class);
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int getHealthInfoAmount() {
        int totalCount;
        try{
            totalCount = articleMapper.countHealthInfo();
            LOGGER.info("查询数据库中,健康资讯的数据数量 ==> " + totalCount);
        }catch(Exception e){
            totalCount = 0;
            LOGGER.error("查询数据库中,健康资讯的数据数量 ==> error ==> " + e.getMessage(), e);
        }
        return totalCount;
    }

    @Override
    public List<HealthInfo> getHealthInfoByPagination(int start, int limit) {
        List<HealthInfo> healthInfoList;
        try{
            List<Article> articleList = articleMapper.selectHealthInfoByPagination(start, limit);
            healthInfoList = HealthInfoModelUtil.tranArticleList(articleList);
            LOGGER.info("查询数据库[start:" + start + ";limit:" + limit + "],健康资讯的信息 ==> " + healthInfoList);
        }catch(Exception e){
            LOGGER.error("查询数据库[start:" + start + ";limit:" + limit + "],健康资讯的信息 ==> error ==> " + e.getMessage(), e);
            healthInfoList = new ArrayList<>();
        }
        return healthInfoList;
    }
}
