package com.taofang.webapi.resource;

import com.taofang.webapi.domain.HealthVoice;
import com.taofang.webapi.domain.HealthVoicePagination;
import com.taofang.webapi.service.IHealthVoiceService;
import com.taofang.webapi.util.HealthVoiceModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
@Path("healthvoices")
public class HealthVoiceResource {
    @Autowired
    private IHealthVoiceService healthVoiceService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public HealthVoicePagination getHealthVoicePagination(@DefaultValue("1") @QueryParam("page") int page,
                                                          @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        // 总数
        int totalCount = healthVoiceService.getHealthVoiceAmountByDate(null);
        // 分页信息
        HealthVoicePagination healthVoicePagination = HealthVoiceModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (healthVoicePagination.getCurPage() - 1) * healthVoicePagination.getPerPage();
        List<HealthVoice> healthVoiceList = healthVoiceService.getHealthVoiceByPagination(null, start, healthVoicePagination.getPerPage());
        healthVoicePagination.setHealthVoiceList(healthVoiceList);

        return healthVoicePagination;
    }

    @GET
    @Path("/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public HealthVoicePagination getHealthVoicePaginationByDatePath(@PathParam("date")String date,
                                                                    @DefaultValue("1") @QueryParam("page") int page,
                                                                    @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        // 总数
        int totalCount = healthVoiceService.getHealthVoiceAmountByDate(date);
        // 分页信息
        HealthVoicePagination healthVoicePagination = HealthVoiceModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (healthVoicePagination.getCurPage() - 1) * healthVoicePagination.getPerPage();
        List<HealthVoice> healthVoiceList = healthVoiceService.getHealthVoiceByPagination(date, start, healthVoicePagination.getPerPage());
        healthVoicePagination.setHealthVoiceList(healthVoiceList);

        return healthVoicePagination;
    }
}
