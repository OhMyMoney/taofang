package com.taofang.webapi.dao;

import com.taofang.webapi.model.Prescription;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrescriptionMapper {
    @Select({
            "select t2.PrescriptionId, t3.Name as ImageUrl, t2.CreatorName, t2.CreateDate",
            "from Member t1 left join Prescription t2 on t1.MemberName = t2.CreatorName",
            "left join PrescriptionMessage t3 ON t2.PrescriptionID = t3.PrescriptionID",
            "where t1.MemberId = #{memberId, jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Prescription> selectPrescriptionByCreator(@Param("memberId") int memberId);
}