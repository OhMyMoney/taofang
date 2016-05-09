package com.taofang.webapi.resource;

import com.taofang.webapi.domain.NatureTherapyInfo;
import com.taofang.webapi.domain.NatureTherapyInfoPagination;
import com.taofang.webapi.domain.NatureTherapyInfoWithLinks;
import com.taofang.webapi.service.INatureTherapyService;
import com.taofang.webapi.util.NatureTherapyModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-04
 */
@Path("naturethearpy")
public class NatureTherapyResouce {
    @Autowired
    private INatureTherapyService natureTherapyService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public NatureTherapyInfoPagination getNatureTherapyInfoPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                                      @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        int totalCount = natureTherapyService.getNatureTherapyAmount();
        NatureTherapyInfoPagination natureTherapyInfoPagination = NatureTherapyModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (natureTherapyInfoPagination.getCurPage() - 1) * natureTherapyInfoPagination.getPerPage();
        List<NatureTherapyInfo> natureTherapyInfoList = natureTherapyService.getNatureTherapyByPagination(start, natureTherapyInfoPagination.getPerPage());
        natureTherapyInfoPagination.setNatureTherapyInfoList(natureTherapyInfoList);

        return natureTherapyInfoPagination;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public NatureTherapyInfoWithLinks getHealthInfoById(@DefaultValue("0")@PathParam("id") int id){
        NatureTherapyInfoWithLinks natureTherapyInfoWithLinks = natureTherapyService.getNatureTherapyInfoWithLinksById(id);
        return natureTherapyInfoWithLinks;
    }
}
