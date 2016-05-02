package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@XmlRootElement(name="storyInfoPagination")
public class StoryInfoPagination {
    // 总的数量
    private int totalCount;
    // 当前页
    private int curPage;
    // 每页数量
    private int perPage;
    // 总共页数
    private int totalPage;
    // 当前页数据
    private List<StoryInfo> storyInfoList;

    @Override
    public String toString() {
        return "StoryInfoPagination{" +
                "totalCount=" + totalCount +
                ", curPage=" + curPage +
                ", perPage=" + perPage +
                ", totalPage=" + totalPage +
                ", storyInfoList=" + storyInfoList +
                '}';
    }

    @XmlElement(name="totalCount")
    public int getTotalCount() {
        return totalCount;
    }
    @XmlElement(name="curPage")
    public int getCurPage() {
        return curPage;
    }
    @XmlElement(name="perPage")
    public int getPerPage() {
        return perPage;
    }
    @XmlElement(name="totalPage")
    public int getTotalPage() {
        return totalPage;
    }
    @XmlElement(name="storyInfos")
    public List<StoryInfo> getStoryInfoList() {
        return storyInfoList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setStoryInfoList(List<StoryInfo> storyInfoList) {
        this.storyInfoList = storyInfoList;
    }
}
