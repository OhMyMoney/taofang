package com.taofang.webapi.resource;

import com.taofang.webapi.domain.DiseaseInfo;
import com.taofang.webapi.domain.DiseaseInfoList;
import com.taofang.webapi.service.IDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
@Path("disease")
public class DiseaseResource {
    @Autowired
    private IDiseaseService diseaseService;

    @Path("statistics")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public DiseaseInfoList getDeseaseSearchStatistics(){
        List<DiseaseInfo> diseaseInfoList = diseaseService.getDiseaseSearchStatistics();

        return new DiseaseInfoList(diseaseInfoList);
    }
}
