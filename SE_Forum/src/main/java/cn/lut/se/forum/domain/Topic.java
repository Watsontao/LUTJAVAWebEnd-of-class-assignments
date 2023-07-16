package cn.lut.se.forum.domain;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户类
 * @Author zhqw
 */
public class Topic {



    private int id;

    private int cId;

    private String title;

    private String content;

    private int pv;


    private int userId;

    private String username;

    private String userImg;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int hot;

    private int delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
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

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }


    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", cId=" + cId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pv=" + pv +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", userImg='" + userImg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", hot=" + hot +
                ", delete=" + delete +
                '}';
    }
}
