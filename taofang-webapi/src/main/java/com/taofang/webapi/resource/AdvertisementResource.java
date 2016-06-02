package com.taofang.webapi.resource;

import com.taofang.webapi.domain.AdvertisementListDomain;
import com.taofang.webapi.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
@Path("ad")
public class AdvertisementResource {
    @Autowired
    private IAdvertisementService advertisementService;

    @GET
    @Path("/{category}")
    @Produces({MediaType.APPLICATION_JSON})
    public AdvertisementListDomain getAdvertisementListByPath(@DefaultValue("")@PathParam("category")String category){
        return advertisementService.getAdvertisementListByCategory(category);
    }
}
