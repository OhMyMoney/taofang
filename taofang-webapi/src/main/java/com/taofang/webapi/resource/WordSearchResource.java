package com.taofang.webapi.resource;

import com.taofang.webapi.domain.WordSearchPaginationDomain;
import com.taofang.webapi.service.IWordSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@Path("word")
public class WordSearchResource {
    @Autowired
    private IWordSearchService wordSearchService;

    @GET
    @Path("/statistics")
    @Produces({MediaType.APPLICATION_JSON})
    public WordSearchPaginationDomain getWordSearchDomainPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                                    @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        return wordSearchService.getWordSearchPaginationDomain(page, pageSize);
    }
}
