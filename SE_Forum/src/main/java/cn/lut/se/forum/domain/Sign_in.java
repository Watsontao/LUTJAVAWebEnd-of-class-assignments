package cn.lut.se.forum.domain;

/**
 * @author vincent
 * @create 2022-11-04 18:15
 */
import java.sql.Date;


public class Sign_in {
    private int number;
    private Date date;
    private String  username;
    private int monDay;
    private int  sumDay;

    public Sign_in() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMonDay() {
        return monDay;
    }

    public void setMonDay(int monDay) {
        this.monDay = monDay;
    }

    public int getSumDay() {
        return sumDay;
    }

    public void setSumDay(int sumDay) {
        this.sumDay = sumDay;
    }

    @Override
    public String toString() {
        return "Sign_in{" +
                "number=" + number +
                ", date=" + date +
                ", username='" + username + '\'' +
                ", monDay=" + monDay +
                ", sumDay=" + sumDay +
                '}';
    }
}

