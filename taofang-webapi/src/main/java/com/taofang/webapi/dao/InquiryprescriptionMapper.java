package com.taofang.webapi.dao;

import com.taofang.webapi.model.Inquiryprescription;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InquiryprescriptionMapper {
    @Select({
            "select InquiryID, Title, createdBy, createdDate",
            "from InquiryPrescription",
            "where createdBy = '${createdBy}'"
    })
    @ResultMap("BaseResultMap")
    List<Inquiryprescription> selectByCreatedBy(@Param("createdBy") int createdBy);
}