package com.taofang.webapi.dao;

import com.taofang.webapi.model.Dtsimage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DtsimageMapper {
    @Select({
            "select count(*) from DTSImage",
            "where Category = #{categoryId, jdbcType=INTEGER} and Music is not null"
    })
    int countByCategory(@Param("categoryId") int categoryId);

    @Select({
            "select id, Title, Music from DTSImage",
            "where Category = #{categoryId, jdbcType=INTEGER} and Music is not null order by Sequence",
            "limit #{start}, #{limit}"
    })
    @ResultMap("BaseResultMap")
    List<Dtsimage> selectByCategoryPagination(@Param("categoryId")int categoryId,
                                              @Param("start")int start,
                                              @Param("limit")int limit);
}