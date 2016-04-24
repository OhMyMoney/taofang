package com.taofang.webapi.service.impl;

import com.taofang.webapi.bean.HealthInfoCondition;
import com.taofang.webapi.domain.HealthInfo;
import com.taofang.webapi.domain.HealthInfoPagination;
import com.taofang.webapi.mock.HealthInfoMock;
import com.taofang.webapi.service.IHealthInfoService;
import com.taofang.webapi.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
@Service
public class HealthInfoService implements IHealthInfoService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthInfoService.class);

    @Override
    public HealthInfoPagination getHealthInfoPaginationByCondition(HealthInfoCondition condition) {
        List<HealthInfo> healthInfoList = HealthInfoMock.mockHealthInfoList();

        HealthInfoPagination healthInfoPagination = new HealthInfoPagination();

        healthInfoPagination.setCurPage(condition.getCurPage());
        healthInfoPagination.setPerPage(condition.getPerPage());
        healthInfoPagination.setTotalCount(healthInfoList.size());
        healthInfoPagination.setTotalPage(PaginationUtil.getTotalPage(healthInfoPagination.getTotalCount(), healthInfoPagination.getPerPage()));
        int start = (healthInfoPagination.getCurPage() - 1) * healthInfoPagination.getPerPage();
        int end = start + healthInfoPagination.getPerPage();
        end = end > healthInfoList.size() ? healthInfoList.size() : end;
        healthInfoPagination.setHealthInfoList(healthInfoList.subList(start, end));

        return healthInfoPagination;
    }
}
