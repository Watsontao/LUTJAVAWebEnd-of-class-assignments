package cn.lut.se.forum.service.impl;

/**
 * @author vincent
 * @create 2022-11-04 18:19
 */
import cn.lut.se.forum.dao.Sign_inDao;
import cn.lut.se.forum.domain.Sign_in;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.Sign_inService;

import java.sql.Date;
import java.util.List;

public class Sign_inServiceImpl implements Sign_inService {
    private Sign_inDao sinDao = new Sign_inDao();

    @Override
    public List<Sign_in> list() {
        return sinDao.Sign_list();

    }

    /*
     *签到
     * */
    @Override
    public void signin(User u) {
        //将已登录对象传递进来
        //首先判断日期与表格日期是否一致
        String date1 = new Date(new java.util.Date().getTime()).toString();//获取当前的时间
        Date date2 = null;
        System.out.println(date1);
        //获取表中时间
        date2 = sinDao.findDate();
        if(!date1.equals(date2.toString())){ //当前日期与数据库记录日期不一致
            // 清空 sign_in 表
            System.out.println("日期不一致!");
            sinDao.clear_sign();
        }
        //添加签到信息
        System.out.println("即将执行 sign() 方法");
        sinDao.sign(u);


    }
}

