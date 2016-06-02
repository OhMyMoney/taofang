package com.taofang.webapi.service.impl;

import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.dao.DtsimageMapper;
import com.taofang.webapi.dao.RelationlinkMapper;
import com.taofang.webapi.domain.*;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Dtsimage;
import com.taofang.webapi.model.Relationlink;
import com.taofang.webapi.service.IArticleService;
import com.taofang.webapi.util.ArticleModelUtil;
import com.taofang.webapi.util.DatetimeUtil;
import com.taofang.webapi.util.RelationLinkModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@Service
public class ArticleService implements IArticleService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    private static final String THUMB_HASH_KEY = "article:thumb:hash";
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RelationlinkMapper relationlinkMapper;
    @Autowired
    private DtsimageMapper dtsimageMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public ArticlePaginationDomain getArticlePaginationDomain(int categoryId, int page, int pageSize) {
        List<ArticleDomain> articleDomainList;
        PaginationDomain pagination;
        try{
            int totalCount = articleMapper.countByCategory(categoryId);
            pagination = new PaginationDomain(page, pageSize, totalCount);
            int start = (page - 1) * pageSize;
            List<ArticleWithBLOBs> articleWithBLOBList = articleMapper.selectByCategoryLimit(categoryId, start, pageSize);
            articleDomainList = ArticleModelUtil.tranArticleWithBLOBsList(articleWithBLOBList);
        }catch(Exception e){
            pagination = new PaginationDomain(page, pageSize);
            articleDomainList = new ArrayList<>();
            LOGGER.error(e.getMessage(), e);
        }
        return new ArticlePaginationDomain(pagination, articleDomainList);
    }

    @Override
    public RitucharyaPaginationDomain getRitucharyaPaginationDomain(int ritucharya, int page, int pageSize) {
        List<RitucharyaDomain> ritucharyaList;
        PaginationDomain pagination;
        try{
            int totalCount = dtsimageMapper.countByCategory(ritucharya);
            pagination = new PaginationDomain(page, pageSize, totalCount);
            int start = (page - 1) * pageSize;
            List<Dtsimage> dtsimageList = dtsimageMapper.selectByCategoryPagination(ritucharya, start, pageSize);
            ritucharyaList = ArticleModelUtil.tranDtsimageList(dtsimageList);
        }catch(Exception e){
            pagination = new PaginationDomain(page, pageSize);
            ritucharyaList = new ArrayList<>();
            LOGGER.error(e.getMessage(), e);
        }

        return new RitucharyaPaginationDomain(pagination, ritucharyaList);
    }

    @Override
    public ArticlePaginationDomain getJKZSPaginationDomain(String queryDateStr, int page, int pageSize) {
        List<ArticleDomain> articleDomainList;
        PaginationDomain pagination;
        try{
            Timestamp beginTime = DatetimeUtil.tranDatetimeStr(queryDateStr + "000000", DatetimeUtil.FORMAT_DEFAULT_MIN);
            Timestamp endTime = DatetimeUtil.tranDatetimeStr(queryDateStr + "235959", DatetimeUtil.FORMAT_DEFAULT_MIN);
            int totalCount = articleMapper.countJKZSByVideodate(beginTime, endTime);
            pagination = new PaginationDomain(page, pageSize, totalCount);
            int start = (page - 1) * pageSize;
            List<ArticleWithBLOBs> articleWithBLOBsList = articleMapper.selectJKZSByVideodate(beginTime, endTime, start, pageSize);
            articleDomainList = ArticleModelUtil.tranArticleWithBLOBsList(articleWithBLOBsList);
        }catch(Exception e){
            pagination = new PaginationDomain(page, pageSize);
            articleDomainList = new ArrayList<>();
            LOGGER.error(e.getMessage(), e);
        }
        return new ArticlePaginationDomain(pagination, articleDomainList);
    }

    @Override
    public ArticleDetailDomain getArticleDetailById(int categoryId, int articleId) {
        ArticleDetailDomain articleDetail;
        try{
            List<ArticleWithBLOBs> articleWithBLOBsList = articleMapper.selectByCategoryArticleId(categoryId, articleId);
            if(articleWithBLOBsList.size() > 0){
                articleDetail = ArticleModelUtil.tranArticleWithBLOBAsDetail(articleWithBLOBsList.get(0));
                articleDetail.setCategory(ArticleCategory.getCategoryNameById(categoryId));
                // 点赞
                if(articleDetail.getCategory().equals(ArticleCategory.WDGS.categoryName)){
                    articleDetail.setThumbCount(getArticleThumb(articleDetail.getArticleId()));
                }
                // 相关链接
                List<Relationlink> relationlinkList = relationlinkMapper.selectBySourceType(categoryId);
                articleDetail.setRelationlinkList(RelationLinkModelUtil.tranRelationLinkListAsDomain(relationlinkList));
            }else{
                articleDetail = new ArticleDetailDomain(0);
            }
        }catch(Exception e){
            articleDetail = new ArticleDetailDomain(0);
            LOGGER.error(e.getMessage(), e);
        }
        return articleDetail;
    }

    @Override
    public boolean updateArticleThumb(ArticleThumbDomain articleThumbDomain) {
        String thumbHashField = "article:" + articleThumbDomain.getArticleId();
        String thumbSetKey = "article:" + articleThumbDomain.getArticleId() + ":" + DatetimeUtil.tranCurrentStrMIN();
        String thumbSetMember = "user:" + articleThumbDomain.getUserId();
        try(Jedis jedis = jedisPool.getResource()){
            boolean canAdd;
            if(!jedis.exists(thumbSetKey)){
                canAdd = jedis.sadd(thumbSetKey, thumbSetMember) == 1;
                jedis.expire(thumbSetKey, 24*3600);
            }else{
                canAdd = jedis.sadd(thumbSetKey, thumbSetMember) == 1;
            }
            if(canAdd){
                jedis.hincrBy(THUMB_HASH_KEY, thumbHashField, 1);
            }else{
                return false;
            }
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public int getArticleThumb(int articleId){
        try(Jedis jedis = jedisPool.getResource()){
            String thumbHashField = "article:" + articleId;
            String thumbCnt = jedis.hget(THUMB_HASH_KEY, thumbHashField);
            return Strings.isNullOrEmpty(thumbCnt) ? 0 : Integer.parseInt(thumbCnt);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public RitucharyaDomain getRitucharyaDomain(int ritucharya, int lastVideoId) {
        RitucharyaDomain ritucharyaDomain = new RitucharyaDomain();
        try{
            List<Dtsimage> dtsimageList = dtsimageMapper.selectByCategoryAndId(ritucharya, lastVideoId);
            if(dtsimageList.size() > 0){
                ritucharyaDomain = ArticleModelUtil.tranDtsimage(dtsimageList.get(0));
            }else{
                ritucharyaDomain.setRitucharyaId(0);
            }
        }catch(Exception e){
            ritucharyaDomain.setRitucharyaId(0);
            LOGGER.error(e.getMessage(), e);
        }
        return ritucharyaDomain;
    }
}
