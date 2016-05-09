package com.taofang.webapi.dao;

import com.taofang.webapi.bean.CommentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentstarMapper {
    List<CommentBean> selectCommentByPrescriptionID(@Param("prescriptionID") int PrescriptionID);
}