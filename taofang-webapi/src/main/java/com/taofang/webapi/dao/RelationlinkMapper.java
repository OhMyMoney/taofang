package com.taofang.webapi.dao;

import com.taofang.webapi.model.Relationlink;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RelationlinkMapper {
    @Select({
            "select id, LinkTitle, LinkUrl, SourceType",
            "from RelationLink",
            "where SourceType = #{sourceType, jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectBySourceType(@Param("sourceType")int sourceType);

    @Select({
            "select id, LinkTitle, LinkUrl, PrescriptionID, SourceType",
            "from RelationLink",
            "where SourceType = 5 and prescriptionID = #{prescriptionID, jdbcType=INTEGER}",
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectPrescriptionLink(@Param("prescriptionID") int prescriptionID);
}