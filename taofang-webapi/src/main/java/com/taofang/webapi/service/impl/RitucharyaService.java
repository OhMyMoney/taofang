package com.taofang.webapi.service.impl;

import com.google.common.base.Optional;
import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.dao.DtsbkmusicMapper;
import com.taofang.webapi.dao.DtsimageMapper;
import com.taofang.webapi.domain.Ritucharya;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Dtsbkmusic;
import com.taofang.webapi.model.Dtsimage;
import com.taofang.webapi.service.IRitucharyaService;
import com.taofang.webapi.util.RitucharyaModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@Service
public class RitucharyaService implements IRitucharyaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RitucharyaService.class);
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private DtsimageMapper dtsimageMapper;
    @Autowired
    private DtsbkmusicMapper dtsbkmusicMapper;

    @Override
    public List<Ritucharya> getRitucharyaList() {
        List<Ritucharya> ritucharyaList;
        try{
            List<ArticleWithBLOBs> articleList = articleMapper.selectAllRitucharya();
            ritucharyaList = RitucharyaModelUtil.tranArticleList(articleList);
            LOGGER.info("查询数据库中,获取季节养身的数据 ==> " + ritucharyaList);
        }catch(Exception e){
            ritucharyaList = new ArrayList<>();
            LOGGER.error("查询数据库中,获取季节养身的数据 ==> error ==> " + e.getMessage(), e);
        }
        return ritucharyaList;
    }

    @Override
    public int getRitucharyaAmount(int categoryId) {
        int count;
        try{
            count = dtsimageMapper.countByCategory(categoryId);
            LOGGER.info("查询数据库中,获取季节养身[category:" + categoryId + "]的数量 ==> " + count);
        }catch(Exception e){
            count = 0;
            LOGGER.error("查询数据库中,获取季节养身[category:" + categoryId + "]的数量 ==> error ==> " + e.getMessage(), e);
        }
        return count;
    }

    @Override
    public List<Ritucharya> getRitucharyaDetailPagination(int categoryId, int start, int limit) {
        List<Ritucharya> ritucharyaList;
        try{
            List<Dtsimage> dtsimageList = dtsimageMapper.selectByCategoryPagination(categoryId, start, limit);
            if(dtsimageList.size() > 0){
                Dtsbkmusic dtsbkmusic = dtsbkmusicMapper.selectLast();
                ritucharyaList = RitucharyaModelUtil.tranDtsimageList(dtsimageList, Optional.fromNullable(dtsbkmusic).or(new Dtsbkmusic()));
            }else{
                ritucharyaList = new ArrayList<>();
            }
            LOGGER.info("查询数据库中,获取季节养身[category:" + categoryId + ";start:" + start + ";limit" + limit + "]的数据 ==> " + ritucharyaList);
        }catch(Exception e){
            LOGGER.error("查询数据库中,获取季节养身[category:" + categoryId + ";start:" + start + ";limit" + limit + "]的数据 ==> error ==> " + e.getMessage(), e);
            ritucharyaList = new ArrayList<>();
        }
        return ritucharyaList;
    }
}
