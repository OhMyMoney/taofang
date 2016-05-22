package com.taofang.webapi.service.impl;

import com.taofang.webapi.bean.CommentBean;
import com.taofang.webapi.bean.PrescriptionInfoBean;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.constant.PrecriptionOrder;
import com.taofang.webapi.dao.ArticleMapper;
import com.taofang.webapi.dao.CommentstarMapper;
import com.taofang.webapi.dao.PrescriptionmessageMapper;
import com.taofang.webapi.dao.RelationlinkMapper;
import com.taofang.webapi.dao.persistence.ElasticsearchDao;
import com.taofang.webapi.domain.*;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Relationlink;
import com.taofang.webapi.service.IPrescriptionService;
import com.taofang.webapi.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
@Service
public class PrescriptionService implements IPrescriptionService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionService.class);
    @Autowired
    private ElasticsearchDao elasticsearchDao;
    @Autowired
    private RelationlinkMapper relationlinkMapper;
    @Autowired
    private PrescriptionmessageMapper prescriptionmessageMapper;
    @Autowired
    private CommentstarMapper commentstarMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PrescriptionPaginationDomain getPrescriptionPaginationDomain(String prescription, String orderName, int page, int pageSize) {
        PrescriptionPaginationDomain prescriptionPagination;
        try{
            int start = (page - 1) * pageSize;
            prescriptionPagination = elasticsearchDao.searchPrescriptionPagination(prescription, orderName, page, start, pageSize);
            // 自然疗法
            int nataropathyCount = articleMapper.countByCategory(2);
            if(start > nataropathyCount){
                int times = start / nataropathyCount;
                start = start - nataropathyCount * times;
            }
            List<ArticleWithBLOBs> articleWithBLOBsList = articleMapper.selectByCategoryLimit(2, start, 5);
            prescriptionPagination.setNataropathylinkList(ArticleModelUtil.tranArticleWithBLOBListAsRelationlink(articleWithBLOBsList));
        }catch(Exception e){
            prescriptionPagination = new PrescriptionPaginationDomain(prescription, PrecriptionOrder.getOrderIdByName(orderName));
            prescriptionPagination.setPagination(new PaginationDomain(page, pageSize));
            LOGGER.error(e.getMessage(), e);
        }

        return prescriptionPagination;
    }

    @Override
    public PrescriptionDetailDomain getPrescriptionDetailDomain(int prescriptionId) {
        PrescriptionDetailDomain prescriptionDetail;
        List<RelationlinkDomain> videolinkList = new ArrayList<>();
        List<RelationlinkDomain> contentlinkList = new ArrayList<>();
        try{
            List<PrescriptionInfoBean> prescriptionInfoBeanList = prescriptionmessageMapper.selectPrescriptionInfoById(prescriptionId);
            if(prescriptionInfoBeanList.size() > 0){
                prescriptionDetail = PrescriptionModelUtil.trantranPrescriptionInfoBeanAsDetail(prescriptionInfoBeanList.get(0));
                // 相关疾病
                String diseaseNames = elasticsearchDao.searchDiseaseById(prescriptionId);
                prescriptionDetail.setDiseaseNames(diseaseNames);
                // 评论数量
                int commentCount = commentstarMapper.countCommentByPrescriptionID(prescriptionId);
                prescriptionDetail.setCommentCount(commentCount);
                // 相关信息
                List<Relationlink> relationlinkList = relationlinkMapper.selectPrescriptionLink(prescriptionId);
                for(Relationlink relationlink : relationlinkList){
                    RelationlinkDomain relationlinkDomain = RelationLinkModelUtil.tranRelationLinkAsDomain(relationlink);
                    if(relationlink.getLinkurl().contains(".mp3")){
                        videolinkList.add(relationlinkDomain);
                    }else{
                        contentlinkList.add(relationlinkDomain);
                    }
                }
                prescriptionDetail.setVideolinkList(videolinkList);
                prescriptionDetail.setContentlinkList(contentlinkList);
            }else{
                prescriptionDetail = new PrescriptionDetailDomain(0);
            }
        }catch(Exception e){
            prescriptionDetail = new PrescriptionDetailDomain(0);
            LOGGER.error(e.getMessage(), e);
        }
        return prescriptionDetail;
    }

    @Override
    public PrescriptionCommentDomain getPrescriptionCommentDomain(int prescriptionId) {
        PrescriptionCommentDomain prescriptionComment;
        try{
            List<CommentBean> commentBeanList = commentstarMapper.selectCommentByPrescriptionID(prescriptionId);
            prescriptionComment = new PrescriptionCommentDomain(prescriptionId, CommentModelUtil.tranCommentBeanListAsDomain(commentBeanList));
        }catch(Exception e){
            prescriptionComment = new PrescriptionCommentDomain(0, new ArrayList<>());
            LOGGER.error(e.getMessage(), e);
        }

        return prescriptionComment;
    }

    @Override
    public PrescriptionMaterialDomain getPrescriptionMaterialDomain(int prescriptionId) {
        PrescriptionMaterialDomain prescriptionMaterial;
        try{
            String material = elasticsearchDao.searchMaterialById(prescriptionId);
            material = material.replaceAll("\\.\\./", "");
            while(material.contains("/Content/Resources/")){
                material = material.replaceAll("/Content/Resources/", "Content/Resources/");
            }
            while(material.contains("&nbsp; ")){
                material = material.replaceAll("&nbsp; ", "&nbsp;");
            }
            material = material.replaceAll("&nbsp;", "");
            material = material.replaceAll("Content/Resources/", ImageConstant.IMAGE_BASE_URL);

            prescriptionMaterial = new PrescriptionMaterialDomain(prescriptionId, material);
        }catch(Exception e){
            prescriptionMaterial = new PrescriptionMaterialDomain(prescriptionId);
            LOGGER.error(e.getMessage(), e);
        }
        return prescriptionMaterial;
    }

}
