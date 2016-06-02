package com.taofang.webapi.resource;

import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.domain.*;
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
    @Path("/list/{category}")
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
    @Path("/ritucharya/list/{ritucharya}")
    @Produces({MediaType.APPLICATION_JSON})
    public RitucharyaPaginationDomain getRitucharyaPaginationByPath(@DefaultValue("0") @PathParam("ritucharya") int ritucharya,
                                                                    @DefaultValue("1") @QueryParam("page") int page,
                                                                    @DefaultValue("10") @QueryParam("pageSize") int pageSize) {
        RitucharyaPaginationDomain ritucharyaPagination = articleService.getRitucharyaPaginationDomain(ritucharya, page, pageSize);

        return ritucharyaPagination;
    }

    @GET
    @Path("/ritucharya/list/video/{ritucharya}")
    @Produces({MediaType.APPLICATION_JSON})
    public RitucharyaDomain getRitucharyaVideoByPath(@DefaultValue("0") @PathParam("ritucharya") int ritucharya,
                                                     @DefaultValue("1") @QueryParam("lastId") int lastId) {
        RitucharyaDomain ritucharyaDomain = articleService.getRitucharyaDomain(ritucharya, lastId);

        return ritucharyaDomain;
    }

    @GET
    @Path("/detail/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ArticleDetailDomain getArticleDetailById(@DefaultValue("0")@PathParam("id")int id,
                                                    @DefaultValue("") @QueryParam("category") String category){
        int categoryId = ArticleCategory.getCategoryIdByName(category);
        ArticleDetailDomain articleDetail = articleService.getArticleDetailById(categoryId, id);

        return articleDetail;
    }

    @POST
    @Path("/thumb")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String updateArticleThumb(ArticleThumbDomain articleThumb){
        if(articleService.updateArticleThumb(articleThumb)){
            return "success;" + articleThumb.getArticleId();
        }else{
            return "fail";
        }
    }

    @GET
    @Path("/thumb/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String getArticleThumb(@DefaultValue("0")@PathParam("id")int id){
        int thumbCnt = articleService.getArticleThumb(id);
        return thumbCnt + "";
    }
}