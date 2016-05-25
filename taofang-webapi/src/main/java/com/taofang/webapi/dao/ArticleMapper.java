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
            "where category = ${category}"
    })
    int countByCategory(@Param("category") int category);

    /*Mysql*/
//    @Select({
//            "select ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
//            "from article",
//            "where category = ${category, jdbcType=INTEGER}",
//            "order by isDTS, ArticleID desc",
//            "limit ${start}, ${limit}"
//    })
//    @ResultMap("ResultMapWithBLOBs")
//    List<ArticleWithBLOBs> selectByCategoryLimit(@Param("category") int category,
//                                                 @Param("start")int start,
//                                                 @Param("limit")int limit);
    /*SQL Server*/
    @Select({
            "select top ${limit} ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
            "from article",
            "where category = ${category}",
            "and ArticleID not in",
            "(select top ${start} ArticleID from article",
            "where category = ${category} order by isDTS, ArticleID desc)",
            "order by isDTS, ArticleID desc",
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectByCategoryLimit(@Param("category") int category,
                                                 @Param("start")int start,
                                                 @Param("limit")int limit);

    @Select({
            "select count(*) from article",
            "where category = 4 and",
            "(videodate between '${beginTime}' and '${endTime}')"
    })
    int countJKZSByVideodate(@Param("beginTime") Timestamp beginTime,
                             @Param("endTime") Timestamp endTime);

    /*Mysql*/
    /*@Select({
            "select ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
            "from article",
            "where category = 4 and ",
            "(videodate between ${beginTime, jdbcType=TIMESTAMP} and ${endTime, jdbcType=TIMESTAMP})",
            "order by ArticleID desc",
            "limit ${start}, ${limit}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectJKZSByVideodate(@Param("beginTime") Timestamp beginTime,
                                                 @Param("endTime") Timestamp endTime,
                                                 @Param("start")int start,
                                                 @Param("limit")int limit);*/
    /*SQL Server*/
    @Select({
            "select top ${limit} ArticleID, ArticleName, Category, ImageUrl, Video, Summary, isDTS",
            "from article",
            "where category = 4 and ",
            "(videodate between '${beginTime}' and '${endTime}')",
            "and ArticleID not in",
            "(select top ${start} ArticleID from article",
            "where category = 4 and",
            "(videodate between '${beginTime}' and '${endTime}')",
            "order by ArticleID desc)",
            "order by ArticleID desc",
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectJKZSByVideodate(@Param("beginTime") Timestamp beginTime,
                                                 @Param("endTime") Timestamp endTime,
                                                 @Param("start")int start,
                                                 @Param("limit")int limit);

    @Select({
            "select ArticleID, ArticleName, ImageUrl, ArticleContent",
            "from Article",
            "where category = ${category}",
            "and ArticleID = ${articleID}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<ArticleWithBLOBs> selectByCategoryArticleId(@Param("category") int category,
                                                     @Param("articleID") int articleId);

}