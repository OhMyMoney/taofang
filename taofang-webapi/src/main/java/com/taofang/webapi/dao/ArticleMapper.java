package com.taofang.webapi.dao;

import com.taofang.webapi.model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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

    @Select({
            "select count(*) from article",
            "where category = 0"
    })
    int countStoryInfo();

    @Select({
            "select ArticleID, ArticleName from article",
            "where category = 0 order by ArticleID desc",
            "limit #{start}, #{limit}"
    })
    @ResultMap("BaseResultMap")
    List<Article> selectStoryInfoByPagination(@Param("start")int start,
                                              @Param("limit")int limit);

    @Select({
            "select count(*) from article",
            "where category = 1"
    })
    int countHealthInfo();

    @Select({
            "select ArticleID, ArticleName from article",
            "where category = 1 order by ArticleID desc",
            "limit #{start}, #{limit}"
    })
    @ResultMap("BaseResultMap")
    List<Article> selectHealthInfoByPagination(@Param("start")int start,
                                               @Param("limit")int limit);

    @Select({
            "select ArticleID, ArticleName, ImageUrl, ArticleContent",
            "from Article",
            "where Category = 1 and ArticleID = #{articleID, jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Article> selectHealthInfoById(@Param("articleID") int articleID);

    @Select({
            "select count(*) from article",
            "where category = 2"
    })
    int countNatureTherapyAmount();

    @Select({
            "select ArticleID, ArticleName from article",
            "where category = 2 order by ArticleID desc",
            "limit #{start}, #{limit}"
    })
    @ResultMap("BaseResultMap")
    List<Article> selectNatureTherapyByPagination(@Param("start")int start,
                                                  @Param("limit")int limit);

}