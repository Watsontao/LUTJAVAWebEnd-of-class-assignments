package cn.lut.se.forum.service.impl;

import cn.lut.se.forum.dao.UserDao;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.service.UserService;
import cn.lut.se.forum.util.CommonUtil;

import java.time.LocalDateTime;
import java.util.Random;
import java.sql.Date;


public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();


    @Override
    public int register(User user) {
        user.setRole(1);
        user.setCreateTime(LocalDateTime.now());
        user.setImg(getRandomImg());
        user.setPwd( CommonUtil.MD5(user.getPwd()) );
        user.setMon_day(0);
        user.setSum_day(0);
        user.setLast_inday(new Date(new java.util.Date().getTime()-86400000));
        //将第一次的签到日期定为注册的前一天 避免值为NULL带来的各种麻烦
        try {
            return userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public User login(String phone, String pwd) {

        String md5pwd = CommonUtil.MD5(pwd);

        //确定是否存在该用户
        User user = userDao.findByPhoneAndPwd(phone,md5pwd);

        return user;
    }

    @Override
    public User login(String phone, String pwd, boolean isMD5) {
        String md5pwd = "";
        if(!isMD5) {
            md5pwd = CommonUtil.MD5(pwd);
        }else {
            md5pwd = pwd;
        }

        User user = userDao.findByPhoneAndPwd(phone,md5pwd);

        return user;
    }

    @Override
    public void cancellation(User user) {

        //自己写  --vincent

    }

    @Override
    //通过电话查询
    public User findByPhone(String phone) {

        User user = userDao.findByPhone(phone);

        return user;
    }


    @Override
    //通过名字查询
    public User findByUsername(String username) {

        User user = userDao.findByUsername(username);

        return user;
    }







    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "static/pic/1.jpg",
            "static/pic/2.jpg",
            "static/pic/3.jpg",
            "static/pic/4.jpg",
            "static/pic/5.jpg",
            "static/pic/6.jpg",
            "static/pic/7.jpg",
            "static/pic/8.jpg",
            "static/pic/9.jpg",
            "static/pic/10.jpg",
            "static/pic/11.jpg",
            "static/pic/12.jpg",
            "static/pic/13.jpg",
            "static/pic/14.jpg",
            "static/pic/15.jpg",
    };

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}
