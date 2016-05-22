package com.taofang.webapi.resource;

import com.taofang.webapi.constant.PrecriptionOrder;
import com.taofang.webapi.domain.*;
import com.taofang.webapi.service.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @Desc 偏方资源类
 * @Author Remilia
 * @Create 2016-04-14
 */
@Path("prescription")
public class PrescriptionResource {
    @Autowired
    private IPrescriptionService prescriptionService;

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionPaginationDomain getPrescriptionPaginationByPath(@QueryParam("prescription") String prescription,
                                                                        @DefaultValue("1") @QueryParam("page") int page,
                                                                        @DefaultValue("5") @QueryParam("pageSize") int pageSize,
                                                                        @DefaultValue("0") @QueryParam("order") int orderId){
        String orderName = PrecriptionOrder.getOrderNameById(orderId);

        PrescriptionPaginationDomain prescriptionPagination = prescriptionService.getPrescriptionPaginationDomain(prescription, orderName, page, pageSize);

        return  prescriptionPagination;
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionDetailDomain getPrescriptionDetailById(@DefaultValue("0") @PathParam("id") int id){
        PrescriptionDetailDomain prescriptionDetail = prescriptionService.getPrescriptionDetailDomain(id);

        return prescriptionDetail;
    }

    @GET
    @Path("/comment/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionCommentDomain getPrescriptionCommentById(@DefaultValue("0") @PathParam("id") int id){
        PrescriptionCommentDomain prescriptionComment = prescriptionService.getPrescriptionCommentDomain(id);

        return prescriptionComment;
    }

    @GET
    @Path("/material/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionMaterialDomain getPrescriptionMaterialById(@DefaultValue("0") @PathParam("id") int id){
        PrescriptionMaterialDomain prescriptionMaterial = prescriptionService.getPrescriptionMaterialDomain(id);

        return prescriptionMaterial;
    }

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
        String orderName = PrecriptionOrder.getOrderNameById(orderId);
        int start = (page - 1) * pageSize;
        PrescriptionPagination prescriptionPagination = prescriptionService.getPrescriptionPagination(name, orderName, start, pageSize);
        prescriptionPagination.setCurPage(page);
        prescriptionPagination.setPerPage(pageSize);
        return prescriptionPagination;
    }

    @GET
    @Path("/relation")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionRelateInfo getPrecriptionRelation(@QueryParam("name") String name) throws IOException {
        PrescriptionRelateInfo prescriptionRelateInfo = prescriptionService.getPrescriptionRelateInfo(name);
        return prescriptionRelateInfo;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public PrescriptionWithLinks getPrecriptionById(@DefaultValue("0")@PathParam("id") int id){
        PrescriptionWithLinks prescriptionWithLinks = prescriptionService.getPrescriptionWithLinksById(id);
        return prescriptionWithLinks;
    }

    @GET
    @Path("/{id}/material")
    @Produces({MediaType.TEXT_PLAIN})
    public String getPrecriptionMaterialById(@DefaultValue("0")@PathParam("id") int id){
        String material = prescriptionService.getMaterialById(id);
        return material;
    }
}
