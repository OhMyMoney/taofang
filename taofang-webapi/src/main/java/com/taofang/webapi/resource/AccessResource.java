package com.taofang.webapi.resource;

import com.taofang.webapi.service.IAccessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-05
 */
@Path("access")
public class AccessResource {
    @Autowired
    private IAccessService accessService;

    @GET
    @Path("/weixin/signature")
    @Produces({MediaType.TEXT_PLAIN})
    public String getWeixinSignature(){
        return accessService.getWeixinAccessSignature();
    }
}
