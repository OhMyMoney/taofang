package com.taofang.webapi.resource;

import com.taofang.webapi.domain.HealthInfo;
import com.taofang.webapi.domain.HealthInfoPagination;
import com.taofang.webapi.service.IHealthInfoService;
import com.taofang.webapi.util.HealthInfoModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
@Path("healthinfo")
public class HealthInfoResource {
    @Autowired
    private IHealthInfoService healthInfoService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public HealthInfoPagination getHealthInfoPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                        @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        int totalCount = healthInfoService.getHealthInfoAmount();
        HealthInfoPagination healthInfoPagination = HealthInfoModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (healthInfoPagination.getCurPage() - 1) * healthInfoPagination.getPerPage();
        List<HealthInfo> healthInfoList = healthInfoService.getHealthInfoByPagination(start, healthInfoPagination.getPerPage());
        healthInfoPagination.setHealthInfoList(healthInfoList);

        return healthInfoPagination;
    }
}
