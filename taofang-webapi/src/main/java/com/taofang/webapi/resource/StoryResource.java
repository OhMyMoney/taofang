package com.taofang.webapi.resource;

import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.domain.StoryInfoPagination;
import com.taofang.webapi.service.IStoryInfoService;
import com.taofang.webapi.util.StoryInfoModelUtil;
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
public class StoryResource {
    @Autowired
    private IStoryInfoService storyInfoService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public StoryInfoPagination getStoryInfoPagination(@DefaultValue("1") @QueryParam("page") int page,
                                                      @DefaultValue("10") @QueryParam("pageSize") int pageSize){
        int totalCount = storyInfoService.getStoryInfoAmount();
        StoryInfoPagination storyInfoPagination = StoryInfoModelUtil.tranTotalCount(page, pageSize, totalCount);
        int start = (storyInfoPagination.getCurPage() - 1) * storyInfoPagination.getPerPage();
        List<StoryInfo> storyInfoList = storyInfoService.getStoryInfoByPagination(start, storyInfoPagination.getPerPage());
        storyInfoPagination.setStoryInfoList(storyInfoList);

        return storyInfoPagination;
    }
}
