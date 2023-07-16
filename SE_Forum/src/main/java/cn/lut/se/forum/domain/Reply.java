package cn.lut.se.forum.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Reply {

    private int id;
    private int topicId;

    private int floor;

    private String content;

    private int userId;

    private String username;

    private String userImg;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int delete;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", floor=" + floor +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", userImg='" + userImg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delete=" + delete +
                '}';
    }
}
