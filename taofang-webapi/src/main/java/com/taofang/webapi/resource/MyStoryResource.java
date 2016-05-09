package com.taofang.webapi.resource;

import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.domain.StoryInfoPagination;
import com.taofang.webapi.domain.StoryInfoWithLinks;
import com.taofang.webapi.service.IMyStoryService;
import com.taofang.webapi.util.MyStoryModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@Path("story")
public class MyStoryResource {
    @Autowired
    private IMyStoryService myStoryService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public StoryInfoPagination getStoryInfoPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                      @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        int totalCount = myStoryService.getStoryInfoAmount();
        StoryInfoPagination storyInfoPagination = MyStoryModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (storyInfoPagination.getCurPage() - 1) * storyInfoPagination.getPerPage();
        List<StoryInfo> storyInfoList = myStoryService.getStoryInfoByPagination(start, storyInfoPagination.getPerPage());
        storyInfoPagination.setStoryInfoList(storyInfoList);

        return storyInfoPagination;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public StoryInfoWithLinks getHealthInfoById(@DefaultValue("0")@PathParam("id") int id){
        StoryInfoWithLinks storyInfoWithLinks = myStoryService.getStoryInfoWithLinksById(id);
        return storyInfoWithLinks;
    }
}
