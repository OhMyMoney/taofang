package com.taofang.webapi.service.impl;

import com.taofang.webapi.dao.WordstatisticsMapper;
import com.taofang.webapi.domain.PaginationDomain;
import com.taofang.webapi.domain.WordSearchDomain;
import com.taofang.webapi.domain.WordSearchPaginationDomain;
import com.taofang.webapi.model.Wordstatistics;
import com.taofang.webapi.service.IWordSearchService;
import com.taofang.webapi.util.WordstatisticsModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@Service
public class WordSearchService implements IWordSearchService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WordSearchService.class);
    @Autowired
    private WordstatisticsMapper wordstatisticsMapper;

    @Override
    public WordSearchPaginationDomain getWordSearchPaginationDomain(int page, int pageSize) {
        PaginationDomain pagination;
        List<WordSearchDomain> wordSearchList;
        try{
            int totalCount = wordstatisticsMapper.countByMorefifty();
            pagination = new PaginationDomain(page, pageSize, totalCount);
            int start = ((page - 1) % 3) * pageSize;
            List<Wordstatistics> wordstatisticsList = wordstatisticsMapper.selectByLimit(start, pageSize);
            wordSearchList = WordstatisticsModelUtil.tranWordstatisticsListAsDomain(wordstatisticsList);
        }catch(Exception e){
            pagination = new PaginationDomain(page, pageSize);
            wordSearchList = new ArrayList<>();
            LOGGER.error(e.getMessage(), e);
        }
        return new WordSearchPaginationDomain(pagination, wordSearchList);
    }
}
