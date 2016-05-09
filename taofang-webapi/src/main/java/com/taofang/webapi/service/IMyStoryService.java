package com.taofang.webapi.service;

import com.taofang.webapi.domain.StoryInfo;
import com.taofang.webapi.domain.StoryInfoWithLinks;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
public interface IMyStoryService {

    int getStoryInfoAmount();

    List<StoryInfo> getStoryInfoByPagination(int start, int limit);

    StoryInfoWithLinks getStoryInfoWithLinksById(int id);
}
