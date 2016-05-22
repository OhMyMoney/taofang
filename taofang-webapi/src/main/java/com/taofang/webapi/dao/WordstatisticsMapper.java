package com.taofang.webapi.dao;

import com.taofang.webapi.model.Wordstatistics;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordstatisticsMapper {
    @Select({
            "select count(*) from WordStatistics",
            "where SearchCount >= 50"
    })
    int countByMorefifty();

    @Select({
            "select WordStatisticsID, SearchCount, Word from WordStatistics",
            "order by SearchCount desc",
            "limit #{start}, #{limit}"
    })
    List<Wordstatistics> selectByLimit(@Param("start") int start,
                                       @Param("limit") int limit);
}