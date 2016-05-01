package com.taofang.webapi.dao;

import com.taofang.webapi.model.Article;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ArticleMapper {
    /**
     * 查询健康之声的数据总数(Category=4)
     * @param videoDate
     * @return
     */
    int countHealthVoiceByVideoDate(@Param("videoDate")Timestamp videoDate);

    /**
     * 查询健康之声的数据信息
     * @param videoDate
     * @param start
     * @param limit
     * @return
     */
    List<Article> selectHealthVoiceByVideoDate(@Param("videoDate")Timestamp videoDate,
                                               @Param("start")int start,
                                               @Param("limit")int limit);
}