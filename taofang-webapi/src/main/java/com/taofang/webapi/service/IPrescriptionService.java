package com.taofang.webapi.service;

import com.taofang.webapi.bean.PrescriptionCondition;
import com.taofang.webapi.domain.PrescriptionPagination;
import com.taofang.webapi.domain.PrescriptionRelateInfo;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public interface IPrescriptionService {
    /**
     * 根据分页查询条件获取分页信息
     * @param condition
     * @return
     */
    PrescriptionPagination getPrescriptionPaginationByCondition(PrescriptionCondition condition);

    /**
     * 根据偏方名称查询偏方相关信息
     * @param prescriptionName
     * @return
     */
    PrescriptionRelateInfo getPrescriptionRelateInfoByName(String prescriptionName);
}
