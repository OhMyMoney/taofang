package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc 健康之声
 * @Author Remilia
 * @Create 2016-05-01
 */
@XmlRootElement(name="healthVoice")
public class HealthVoice {
    // id
    private int id;
    // 标题
    private String title;
    // 音频URL
    private String url;

    @Override
    public String toString() {
        return "HealthVoice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    @XmlElement(name="url")
    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
