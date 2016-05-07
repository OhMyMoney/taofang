package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.dao.RelationlinkMapper;
import com.taofang.webapi.domain.RelationLinkInfo;
import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.domain.StoryInfoWithLinks;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Relationlink;
import com.taofang.webapi.service.IMyStoryService;
import com.taofang.webapi.util.MyStoryModelUtil;
import com.taofang.webapi.util.RelationLinkModelUtil;
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
public class MyStoryService implements IMyStoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyStoryService.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RelationlinkMapper relationlinkMapper;

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
            List<ArticleWithBLOBs> articleList = articleMapper.selectStoryInfoByPagination(start, limit);
            storyInfoList = MyStoryModelUtil.tranArticleList(articleList);
            LOGGER.info("查询数据库[start:" + start + ";limit:" + limit + "],我的故事的信息 ==> " + storyInfoList);
        }catch(Exception e){
            LOGGER.error("查询数据库[start:" + start + ";limit:" + limit + "],我的故事的信息 ==> error ==> " + e.getMessage(), e);
            storyInfoList = new ArrayList<>();
        }
        return storyInfoList;
    }

    @Override
    public StoryInfoWithLinks getStoryInfoWithLinksById(int id) {
        StoryInfo storyInfo = new StoryInfo();
        List<RelationLinkInfo> relationLinkInfoList = new ArrayList<>();
        try{
            List<ArticleWithBLOBs> articleList = articleMapper.selectStoryInfoById(id);
            if(articleList.size() > 0){
                storyInfo = MyStoryModelUtil.tranArticle(articleList.get(0));
                List<Relationlink> relationlinkList = relationlinkMapper.selectStoryInfoLink();
                relationLinkInfoList = RelationLinkModelUtil.tranRelationLinkList(relationlinkList);
            }else{
                storyInfo.setId(0);
            }
            LOGGER.info("根据Id:[" + id + "]查询数据库中我的故事的详细信息 ==> " + storyInfo);
        }catch(Exception e){
            storyInfo.setId(0);
            LOGGER.error("根据Id:[" + id + "]查询数据库中我的故事的详细信息 ==> error ==> " + e.getMessage(), e);
        }
        return new StoryInfoWithLinks(storyInfo, relationLinkInfoList);
    }
}
