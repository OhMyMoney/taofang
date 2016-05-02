package com.taofang.webapi.service;

import com.taofang.webapi.domain.StoryInfo;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
public interface IStoryInfoService{

    int getStoryInfoAmount();

    List<StoryInfo> getStoryInfoByPagination(int start, int limit);
}
