package cn.lut.se.forum.dao;

/**
 * @author vincent
 * @create 2022-11-04 18:19
 */
import cn.lut.se.forum.domain.Sign_in;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import static java.time.LocalDate.now;

public class Sign_inDao {
    QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);


    /*
     * 返回签到人员列表
     * */
    public List<Sign_in> Sign_list(){

        String sql = "select * from sign_in order by number desc;";//按照签到顺序 从大到小排
        List<Sign_in> list = null;

        try {
            list  = queryRunner.query(sql,new BeanListHandler<>(Sign_in.class,processor));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    /*
    返回 signin 表中 的 Date
     */
    public Date findDate()  {
        String sql = "select * from sign_in ;";
        System.out.println("正在查询数据库日期");
        Object[] d = new Object[0];
        try {
            //只存一条数据
            d = queryRunner.query(sql,new ArrayHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  (Date) d[1];
    }

    /*
     * 清空签到表
     * */
    public void clear_sign()  {
        System.out.println("清空签到表");
        String sql = "delete from sign_in;";
        try {
            queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 向签到表中插入数据
     * */
    public void sign(User user) {
        System.out.println("进入签到方法 sign()");
        if(exit_in(user)){//检查是否已经签过了
            //未签
            //更新月签到天数 和 总签到天数
            dayup(user);
            String sql = "select * from sign_in;";
            int n;
            try {
                n = queryRunner.query(sql,new ArrayListHandler()).size();//获取今天已经签到的人数
                Object[] d;
                sql = "select * from user where username = ?";
                d = queryRunner.query(sql,new ArrayHandler(),user.getUsername());
                System.out.println("当前 n == " + n);
                sql = "insert  into sign_in values(?,?,?,?,?)";
                queryRunner.insert(sql, new ArrayHandler(), n + 1, now(), user.getUsername(), d[8], d[9]);


            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(sql);
            }
        }else {
            //已签
            System.out.println("你他妈的今天已经签过了你知道不！");
        }

    }

    /*
     * 更新用户的月签到天数 和 总签到天数
     */
    public void dayup(User user){
        String sql = "select * from user where username = ?";
        Object[] d = new Object[0];
        try {
            //只存一条数据
            d = queryRunner.query(sql,new ArrayHandler(),user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Calendar calender = Calendar.getInstance();
        int mon_day = (int) d[8],

                sum_day = (int) d[9];
        int nowYear = calender.get(Calendar.YEAR),
                nowMonth = calender.get(Calendar.MONTH)+1;
        calender.setTime((java.util.Date) d[10]);
        int dataYear = calender.get(Calendar.YEAR),
                dataMonth = calender.get(Calendar.MONTH)+1;
        if(nowYear!=dataYear||nowMonth!=dataMonth){
            //月天数初始化为1
            mon_day = 1;
        }else {
            //月天数加一
            mon_day++;
        }
        //总天数加一
        sum_day++;
        System.out.println(" yue  sum uname" +mon_day +' ' + sum_day + ' ' +user.getUsername() );

        System.out.println(d.toString());
        //更新user表中的last_inday
        sql = "update user set mon_day = ? , sum_day = ?,last_inday = ? where username = ?";
        try {
            queryRunner.update(sql,mon_day,sum_day,new Date(new java.util.Date().getTime()),user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*
     * 检查 用户 是否存在 签到页 中
     * 即 防止用户一天内措辞签到
     * */
    public boolean exit_in(User user){
        System.out.println("进入用户签到查重方法");
        String sql = "select * from sign_in where username = ?";
        try {
            int n = queryRunner.query(sql,new ArrayListHandler(),user.getUsername()).size();
            if(n == 0)return  true; // 表中不存在该用户的当天签到情况 可以签到
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
