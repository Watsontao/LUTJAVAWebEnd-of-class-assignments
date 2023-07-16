package cn.lut.se.forum.dao;

import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UserDao {

    //给出一个查询执行器  --vincent
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);


    //创建一个新用户  --vincent
    public int save(User user) throws Exception {
        //校验手机号
        System.out.println("本次测试请看一下测试结果");
        System.out.println(" pwd  ============= " + user.getPwd());
        System.out.println("flag ====== " + ("".equals(user.getPwd()) || user.getPwd() == null));
        boolean flag = Pattern.matches("^1[3-9]\\d{9}$", user.getPhone());
        if (!flag) {
            return 0;
        }
        if ("".equals(user.getUsername()) || user.getUsername() == null) return 0;
        // md5 加密 后的密码 北村放到 pwd 里 这里应该改 md5 哪里
        if ("".equals(user.getPwd()) || user.getPwd() == null) return 0;
        if ("".equals(user.getPhone()) || user.getPhone() == null) {
            return 0;
        }
        //检查用户名是否重复
        QueryRunner qr = new QueryRunner(new DataSourceUtil().getDataSource());
        String s1 = "select * from user where username = ?";

        int i = 0;
        try {
            User u = qr.query(s1, new BeanHandler<>(User.class), user.getUsername());
            if (u == null) {// 无此用户名 可以注册
                String sql = "insert into user (phone,pwd,sex,img,create_time,role,username,mon_day,sum_day,last_inday) values(?,?,?,?,?,?,?,?,?,?)";
                Object[] params = {
                        user.getPhone(), user.getPwd(), user.getSex(), user.getImg(), user.getCreateTime(), user.getRole(), user.getUsername(), user.getMon_day(), user.getSum_day(), user.getLast_inday()
                };
                try {
                    return queryRunner.update(sql, params);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception();
                }
            } else {//不可以注册
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    //通过电话和密码查找用户  --vincent
    public User findByPhoneAndPwd(String phone, String md5pwd) {

        String sql = "select * from user where phone=? and pwd=?";

        User user = null;

        try {

            user = queryRunner.query(sql, new BeanHandler<>(User.class, processor), phone, md5pwd);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }



    //通过电话查找用户  --vincent
    public User findByPhone(String phone ) {

        String sql = "select * from user where phone=?";

        User user = null;

        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class, processor), phone);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


    //通过用户名查找用户  --vincent
    public User findByUsername(String username ) {

        String sql = "select * from user where username=?";

        User user = null;

        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class, processor), username);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
