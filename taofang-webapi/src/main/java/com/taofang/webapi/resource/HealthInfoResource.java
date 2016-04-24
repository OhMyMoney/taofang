package com.taofang.webapi.resource;

import com.taofang.webapi.bean.HealthInfoCondition;
import com.taofang.webapi.domain.HealthInfoPagination;
import com.taofang.webapi.service.IHealthInfoService;
import com.taofang.webapi.util.HealthInfoModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
@Path("healthinfos")
public class HealthInfoResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthInfoResource.class);
    @Autowired
    private IHealthInfoService healthInfoService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public HealthInfoPagination getHealthInfoPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                        @DefaultValue("5") @QueryParam("pageSize") int pageSize){
        HealthInfoCondition condition = HealthInfoModelUtil.tranHealthInfoCondition(page, pageSize);
        HealthInfoPagination healthInfoPagination = healthInfoService.getHealthInfoPaginationByCondition(condition);

        return healthInfoPagination;
    }
}
