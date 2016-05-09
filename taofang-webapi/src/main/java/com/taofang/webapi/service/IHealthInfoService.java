package com.taofang.webapi.service;

import com.taofang.webapi.domain.HealthInfo;
import com.taofang.webapi.domain.HealthInfoWithLinks;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public interface IHealthInfoService {
    int getHealthInfoAmount();

    List<HealthInfo> getHealthInfoByPagination(int start, int limit);

    HealthInfoWithLinks getHealthInfoWithLinksById(int id);
}
