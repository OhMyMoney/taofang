package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.dao.RelationlinkMapper;
import com.taofang.webapi.domain.NatureTherapyInfo;
import com.taofang.webapi.domain.NatureTherapyInfoWithLinks;
import com.taofang.webapi.domain.RelationLinkInfo;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Relationlink;
import com.taofang.webapi.service.INatureTherapyService;
import com.taofang.webapi.util.NatureTherapyModelUtil;
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
 * @Create 2016-05-04
 */
@Service
public class NatureTherapyService implements INatureTherapyService{
    private static final Logger LOGGER = LoggerFactory.getLogger(NatureTherapyService.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RelationlinkMapper relationlinkMapper;

    @Override
    public int getNatureTherapyAmount() {
        int totalCount;
        try{
            totalCount = articleMapper.countNatureTherapyAmount();
            LOGGER.info("查询数据库中,自然疗法的数据数量 ==> " + totalCount);
        }catch(Exception e){
            totalCount = 0;
            LOGGER.error("查询数据库中,自然疗法的数据数量 ==> error ==> " + e.getMessage(), e);
        }
        return totalCount;
    }

    @Override
    public List<NatureTherapyInfo> getNatureTherapyByPagination(int start, int limit) {
        List<NatureTherapyInfo> natureTherapyInfoList;
        try{
            List<ArticleWithBLOBs> articleList = articleMapper.selectNatureTherapyByPagination(start, limit);
            natureTherapyInfoList = NatureTherapyModelUtil.tranArticleList(articleList);
            LOGGER.info("查询数据库[start:" + start + ";limit:" + limit + "],自然疗法的信息 ==> " + natureTherapyInfoList);
        }catch(Exception e){
            LOGGER.error("查询数据库[start:" + start + ";limit:" + limit + "],自然疗法的信息 ==> error ==> " + e.getMessage(), e);
            natureTherapyInfoList = new ArrayList<>();
        }
        return natureTherapyInfoList;
    }

    @Override
    public NatureTherapyInfoWithLinks getNatureTherapyInfoWithLinksById(int id) {
        NatureTherapyInfo natureTherapyInfo = new NatureTherapyInfo();
        List<RelationLinkInfo> relationLinkInfoList = new ArrayList<>();
        try{
            List<ArticleWithBLOBs> articleList = articleMapper.selectNatureTherapyById(id);
            if(articleList.size() > 0){
                natureTherapyInfo = NatureTherapyModelUtil.tranArticle(articleList.get(0));
                List<Relationlink> relationlinkList = relationlinkMapper.selectNatureTherapyLink();
                relationLinkInfoList = RelationLinkModelUtil.tranRelationLinkList(relationlinkList);
            }else{
                natureTherapyInfo.setId(0);
            }
            LOGGER.info("根据Id:[" + id + "]查询数据库中自然疗法的详细信息 ==> " + natureTherapyInfo);
        }catch(Exception e){
            natureTherapyInfo.setId(0);
            LOGGER.error("根据Id:[" + id + "]查询数据库中自然疗法的详细信息 ==> error ==> " + e.getMessage(), e);
        }
        return new NatureTherapyInfoWithLinks(natureTherapyInfo, relationLinkInfoList);
    }
}
