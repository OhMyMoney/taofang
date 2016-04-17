package com.taofang.webapi.service;

import com.taofang.webapi.bean.HealthInfoCondition;
import com.taofang.webapi.domain.HealthInfoPagination;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public interface IHealthInfoService {

    HealthInfoPagination getHealthInfoPaginationByCondition(HealthInfoCondition condition);
}
