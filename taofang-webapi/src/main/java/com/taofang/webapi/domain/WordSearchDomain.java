package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
@XmlRootElement(name="wordSearch")
public class WordSearchDomain {
    private long wordId;

    private String wordName;

    private String wordType;

    private long searchCount;

    public WordSearchDomain() {
    }

    public WordSearchDomain(long wordId, String wordName, String wordType, long searchCount) {
        this.wordId = wordId;
        this.wordName = wordName;
        this.wordType = wordType;
        this.searchCount = searchCount;
    }
    @XmlElement(name="wordId")
    public long getWordId() {
        return wordId;
    }
    @XmlElement(name="wordName")
    public String getWordName() {
        return wordName;
    }
    @XmlElement(name="wordType")
    public String getWordType() {
        return wordType;
    }
    @XmlElement(name="searchCount")
    public long getSearchCount() {
        return searchCount;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public void setSearchCount(long searchCount) {
        this.searchCount = searchCount;
    }
}
