package com.taofang.webapi.resource;

import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.domain.ArticlePaginationDomain;
import com.taofang.webapi.domain.RitucharyaPaginationDomain;
import com.taofang.webapi.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
@Path("article")
public class ArticleResource {
    @Autowired
    private IArticleService articleService;

    @GET
    @Path("list/{category}")
    @Produces({MediaType.APPLICATION_JSON})
    public ArticlePaginationDomain getArticlePaginationByPath(@DefaultValue("") @PathParam("category") String category,
                                                              @DefaultValue("1") @QueryParam("page") int page,
                                                              @DefaultValue("10") @QueryParam("pageSize") int pageSize,
                                                              @DefaultValue("") @QueryParam("queryDate") String queryDate ){
        int categoryId = ArticleCategory.getCategoryIdByName(category);
        ArticlePaginationDomain articlePagination;
        if(categoryId == 4 && !Strings.isNullOrEmpty(queryDate)){ // 健康之声
            articlePagination = articleService.getJKZSPaginationDomain(queryDate, page, pageSize);
        }else {
            articlePagination = articleService.getArticlePaginationDomain(categoryId, page, pageSize);
        }

        return articlePagination;
    }

    @GET
    @Path("ritucharya/list/{ritucharya}")
    @Produces({MediaType.APPLICATION_JSON})
    public RitucharyaPaginationDomain getRitucharyaPaginationByPath(@DefaultValue("0") @PathParam("ritucharya") int ritucharya,
                                                                    @DefaultValue("1") @QueryParam("page") int page,
                                                                    @DefaultValue("10") @QueryParam("pageSize") int pageSize) {
        RitucharyaPaginationDomain ritucharyaPagination = articleService.getRitucharyaPaginationDomain(ritucharya, page, pageSize);

        return ritucharyaPagination;
    }

}