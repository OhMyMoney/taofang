package com.taofang.webapi.resource;

import com.taofang.webapi.domain.Ritucharya;
import com.taofang.webapi.domain.RitucharyaPagination;
import com.taofang.webapi.service.IRitucharyaService;
import com.taofang.webapi.util.RitucharyaModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@Path("ritucharya")
public class RitucharyaResource {
    @Autowired
    private IRitucharyaService ritucharyaService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public RitucharyaPagination getRitucharyaPagination(){
        RitucharyaPagination ritucharyaPagination = new RitucharyaPagination();
        ritucharyaPagination.setRitucharyaList(ritucharyaService.getRitucharyaList());

        return ritucharyaPagination;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RitucharyaPagination getRitucharyaPaginationByDTSId(@DefaultValue("0") @PathParam("id") int id,
                                                               @DefaultValue("1") @QueryParam("page") int page,
                                                               @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        int totalCount = ritucharyaService.getRitucharyaAmount(id);
        RitucharyaPagination ritucharyaPagination = RitucharyaModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (ritucharyaPagination.getCurPage() - 1) * ritucharyaPagination.getPerPage();
        List<Ritucharya> ritucharyaList = ritucharyaService.getRitucharyaDetailPagination(id, start, ritucharyaPagination.getPerPage());
        ritucharyaPagination.setRitucharyaList(ritucharyaList);

        return ritucharyaPagination;
    }
}
