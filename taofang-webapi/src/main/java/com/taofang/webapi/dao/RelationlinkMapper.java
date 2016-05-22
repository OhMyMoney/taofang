package com.taofang.webapi.dao;

import com.taofang.webapi.model.Relationlink;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RelationlinkMapper {
    @Select({
            "select id, LinkTitle, LinkUrl",
            "from RelationLink",
            "where SourceType = #{sourceType, jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectBySourceType(@Param("sourceType")int sourceType);

    @Select({
            "select id, LinkTitle, LinkUrl, PrescriptionID",
            "from RelationLink",
            "where SourceType = 5 and prescriptionID = #{prescriptionID, jdbcType=INTEGER}",
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectPrescriptionLink(@Param("prescriptionID") int prescriptionID);





    @Select({
            "select id, LinkTitle, LinkUrl",
            "from RelationLink",
            "where SourceType = 0"
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectStoryInfoLink();

    @Select({
            "select id, LinkTitle, LinkUrl",
            "from RelationLink",
            "where SourceType = 1"
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectHealthInfoLink();

    @Select({
            "select id, LinkTitle, LinkUrl",
            "from RelationLink",
            "where SourceType = 2"
    })
    @ResultMap("BaseResultMap")
    List<Relationlink> selectNatureTherapyLink();


}