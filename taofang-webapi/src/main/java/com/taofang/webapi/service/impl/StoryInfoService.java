package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.model.Article;
import com.taofang.webapi.service.IStoryInfoService;
import com.taofang.webapi.util.StoryInfoModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@Service
public class StoryInfoService implements IStoryInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoryInfoService.class);
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int getStoryInfoAmount() {
        int totalCount;
        try{
            totalCount = articleMapper.countStoryInfo();
            LOGGER.info("查询数据库中,我的故事的数据数量 ==> " + totalCount);
        }catch(Exception e){
            totalCount = 0;
            LOGGER.error("查询数据库中,我的故事的数据数量 ==> error ==> " + e.getMessage(), e);
        }
        return totalCount;
    }

    @Override
    public List<StoryInfo> getStoryInfoByPagination(int start, int limit) {
        List<StoryInfo> storyInfoList;
        try{
            List<Article> articleList = articleMapper.selectStoryInfoByPagination(start, limit);
            storyInfoList = StoryInfoModelUtil.tranArticleList(articleList);
            LOGGER.info("查询数据库[start:" + start + ";limit:" + limit + "],我的故事的信息 ==> " + storyInfoList);
        }catch(Exception e){
            LOGGER.error("查询数据库[start:" + start + ";limit:" + limit + "],我的故事的信息 ==> error ==> " + e.getMessage(), e);
            storyInfoList = new ArrayList<>();
        }
        return storyInfoList;
    }
}
