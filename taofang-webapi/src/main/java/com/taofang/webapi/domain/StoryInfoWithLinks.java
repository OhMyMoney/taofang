package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
@XmlRootElement(name="storyInfoWithLinks")
public class StoryInfoWithLinks {
    private StoryInfo storyInfo;

    private List<RelationLinkInfo> relationLinkInfoList;

    public StoryInfoWithLinks() {
    }

    public StoryInfoWithLinks(StoryInfo storyInfo, List<RelationLinkInfo> relationLinkInfoList) {
        this.storyInfo = storyInfo;
        this.relationLinkInfoList = relationLinkInfoList;
    }

    @XmlElement(name="storyInfo")
    public StoryInfo getStoryInfo() {
        return storyInfo;
    }
    @XmlElement(name="relationLinkInfos")
    public List<RelationLinkInfo> getRelationLinkInfoList() {
        return relationLinkInfoList;
    }

    public void setStoryInfo(StoryInfo storyInfo) {
        this.storyInfo = storyInfo;
    }

    public void setRelationLinkInfoList(List<RelationLinkInfo> relationLinkInfoList) {
        this.relationLinkInfoList = relationLinkInfoList;
    }
}
