package com.taofang.webapi.dao;

import com.taofang.webapi.model.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface ArticleMapper {
    @Select({
            "select count(*) from article",
            "where category = #{category, jdbcType=INTEGER}"
    })
    int countByCategory(@Param("category") int category);

    @Select({
            "select ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
            "from article",
            "where category = #{category, jdbcType=INTEGER}",
            "order by isDTS, ArticleID desc",
            "limit #{start}, #{limit}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectByCategoryLimit(@Param("category") int category,
                                                 @Param("start")int start,
                                                 @Param("limit")int limit);

    @Select({
            "select count(*) from article",
            "where category = 4 and",
            "(videodate between #{beginTime, jdbcType=TIMESTAMP} and #{endTime, jdbcType=TIMESTAMP})"
    })
    int countJKZSByVideodate(@Param("beginTime") Timestamp beginTime,
                             @Param("endTime") Timestamp endTime);

    @Select({
            "select ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
            "from article",
            "where category = 4 and ",
            "(videodate between #{beginTime, jdbcType=TIMESTAMP} and #{endTime, jdbcType=TIMESTAMP})",
            "order by ArticleID desc",
            "limit #{start}, #{limit}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectJKZSByVideodate(@Param("beginTime") Timestamp beginTime,
                                                 @Param("endTime") Timestamp endTime,
                                                 @Param("start")int start,
                                                 @Param("limit")int limit);

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
    List<ArticleWithBLOBs> selectHealthVoiceByVideoDate(@Param("videoDate")Timestamp videoDate,
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
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectStoryInfoByPagination(@Param("start")int start,
                                              @Param("limit")int limit);

    @Select({
            "select ArticleID, ArticleName, ImageUrl, ArticleContent",
            "from Article",
            "where Category = 0 and ArticleID = #{articleID, jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectStoryInfoById(@Param("articleID") int articleID);

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
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectHealthInfoByPagination(@Param("start")int start,
                                               @Param("limit")int limit);

    @Select({
            "select ArticleID, ArticleName, ImageUrl, ArticleContent",
            "from Article",
            "where Category = 1 and ArticleID = #{articleID, jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectHealthInfoById(@Param("articleID") int articleID);

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
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectNatureTherapyByPagination(@Param("start")int start,
                                                  @Param("limit")int limit);

    @Select({
            "select ArticleID, ArticleName, ImageUrl, ArticleContent",
            "from Article",
            "where Category = 2 and ArticleID = #{articleID, jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectNatureTherapyById(@Param("articleID") int articleID);

    @Select({
            "select ArticleId, ArticleName, ImageUrl, Summary, isDTS",
            "from Article",
            "where Category = 7 order by isDTS asc;"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectAllRitucharya();
}