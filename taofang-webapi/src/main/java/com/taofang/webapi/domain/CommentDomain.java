package com.taofang.webapi.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-21
 */
@XmlRootElement(name="comment")
public class CommentDomain {
    private int commentId;

    private String memberName;

    private String memberIcon;

    private String commentContent;

    private String commentTime;

    private double score;

    @XmlElement(name="commentId")
    public int getCommentId() {
        return commentId;
    }
    @XmlElement(name="memberName")
    public String getMemberName() {
        return memberName;
    }
    @XmlElement(name="memberIcon")
    public String getMemberIcon() {
        return memberIcon;
    }
    @XmlElement(name="commentContent")
    public String getCommentContent() {
        return commentContent;
    }
    @XmlElement(name="commentTime")
    public String getCommentTime() {
        return commentTime;
    }
    @XmlElement(name="score")
    public double getScore() {
        return score;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
