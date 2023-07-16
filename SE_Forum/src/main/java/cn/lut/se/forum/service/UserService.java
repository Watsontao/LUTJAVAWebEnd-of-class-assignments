package cn.lut.se.forum.service;

import cn.lut.se.forum.domain.User;

public interface  UserService {


    int register(User user);

    User login(String phone, String pwd);

    User login(String phone, String pwd, boolean isMD5);

    void cancellation(User user);

    User findByPhone(String phone );

    User findByUsername(String Username );



}
