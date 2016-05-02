package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-02
 */
@XmlRootElement(name="storyInfo")
public class StoryInfo {
    // id
    private Integer id;
    // 标题
    private String title;
    // 内容
    private List<String> contents;

    @Override
    public String toString() {
        return "StoryInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents=" + contents +
                '}';
    }

    @XmlElement(name="id")
    public Integer getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="contents")
    public List<String> getContents() {
        return contents;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
}
