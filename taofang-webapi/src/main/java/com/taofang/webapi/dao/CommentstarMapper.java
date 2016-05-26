package com.taofang.webapi.dao;

import com.taofang.webapi.bean.CommentBean;
import com.taofang.webapi.model.CommentstarWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentstarMapper {

    @Select({
            "select count(*) from CommentStar",
            "where PrescriptionID = ${prescriptionID}"
    })
    int countCommentByPrescriptionID(@Param("prescriptionID") int PrescriptionID);

    List<CommentBean> selectCommentByPrescriptionID(@Param("prescriptionID") int PrescriptionID);

    @Select({
            "select CommentID, Experience, CreatedDate from CommentStar",
            "where memberId = ${memberId}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<CommentstarWithBLOBs> selectCommentByMemberId(@Param("memberId") int memberId);
}