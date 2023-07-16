package cn.lut.se.forum.domain;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户类
 * @Author zhqw
 */



public class User {
    private int id;

    private int mon_day;//月天数

    private int sum_day;//总天数

    private Date last_inday;//最后一次签到的日期

    private String phone;

    private String pwd;

    private int sex;

    private String img;

    private LocalDateTime createTime;

    private int role;

    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mon_day=" + mon_day +
                ", sum_day=" + sum_day +
                ", last_inday=" + last_inday +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", role=" + role +
                ", username='" + username + '\'' +
                '}';
    }

    public int getMon_day() { return mon_day; }

    public void setMon_day(int mon_day) { this.mon_day = mon_day; }

    public int getSum_day() { return sum_day; }

    public void setSum_day(int sum_day) { this.sum_day = sum_day; }

    public Date getLast_inday() { return last_inday; }

    public void setLast_inday(Date last_inday) { this.last_inday = last_inday; }
}

