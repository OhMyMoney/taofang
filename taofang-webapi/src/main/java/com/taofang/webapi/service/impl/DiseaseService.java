package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.WordstatisticsMapper;
import com.taofang.webapi.domain.DiseaseInfo;
import com.taofang.webapi.model.Wordstatistics;
import com.taofang.webapi.service.IDiseaseService;
import com.taofang.webapi.util.DiseaseModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
@Service
public class DiseaseService implements IDiseaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiseaseService.class);

    @Autowired
    private WordstatisticsMapper wordstatisticsMapper;

    @Override
    public List<DiseaseInfo> getDiseaseSearchStatistics() {
        List<DiseaseInfo> diseaseInfoList;
        try{
            List<Wordstatistics> wordstatisticsList = wordstatisticsMapper.selectByLimit(0, 15);
            diseaseInfoList = DiseaseModelUtil.tranWordstatisticsList(wordstatisticsList);
            LOGGER.info("查询疾病相关词语搜索次数 ==> " + diseaseInfoList);
        }catch(Exception e){
            LOGGER.error("查询疾病相关词语搜索次数 ==> error ==> " + e.getMessage(), e);
            diseaseInfoList = new ArrayList<>();
        }
        return diseaseInfoList;
    }
}
