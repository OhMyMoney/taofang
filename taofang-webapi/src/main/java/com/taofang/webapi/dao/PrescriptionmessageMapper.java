package com.taofang.webapi.dao;

import com.taofang.webapi.bean.PrescriptionInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionmessageMapper {

    List<PrescriptionInfoBean> selectPrescriptionInfoById(@Param("prescriptionId") Integer prescriptionId);
}