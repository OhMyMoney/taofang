package com.taofang.webapi.resource;

import com.taofang.webapi.constant.PrecriptionOrderEnum;
import com.taofang.webapi.domain.PrescriptionPagination;
import com.taofang.webapi.domain.PrescriptionRelateInfo;
import com.taofang.webapi.domain.PrescriptionWithLinks;
import com.taofang.webapi.service.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc 偏方资源类
 * @Author Remilia
 * @Create 2016-04-14
 */
@Path("prescription")
public class PrescriptionResource {
    @Autowired
    private IPrescriptionService prescriptionService;

    /**
     * 查询偏方分页信息
     * @param name
     * @param page
     * @param pageSize
     * @param orderId
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionPagination getPrescriptionPagination(@QueryParam("name") String name,
                                                        @DefaultValue("1") @QueryParam("page") int page,
                                                        @DefaultValue("5") @QueryParam("pageSize") int pageSize,
                                                        @DefaultValue("0") @QueryParam("order") int orderId){
        String orderName = PrecriptionOrderEnum.getOrderNameById(orderId);
        int start = (page - 1) * pageSize;
        PrescriptionPagination prescriptionPagination = prescriptionService.getPrescriptionPagination(name, orderName, start, pageSize);
        prescriptionPagination.setCurPage(page);
        prescriptionPagination.setPerPage(pageSize);
        return prescriptionPagination;
    }

    /**
     * 根据偏方名称查询偏方相关信息
     * @param name
     * @return
     */
    @GET
    @Path("relate")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionRelateInfo getPrescriptionRelateInfo(@QueryParam("name") String name){
        PrescriptionRelateInfo prescriptionRelateInfo = prescriptionService.getPrescriptionRelateInfoByName(name);

        return prescriptionRelateInfo;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionWithLinks getPrecriptionById(@DefaultValue("0")@PathParam("id") int id){
        PrescriptionWithLinks prescriptionWithLinks = prescriptionService.getPrescriptionWithLinksById(id);
        return prescriptionWithLinks;
    }
}
