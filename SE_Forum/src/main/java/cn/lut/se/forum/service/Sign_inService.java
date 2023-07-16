package cn.lut.se.forum.service;

/**
 * @author vincent
 * @create 2022-11-04 18:18
 */
import cn.lut.se.forum.domain.Sign_in;
import cn.lut.se.forum.domain.User;

import java.util.List;

public interface Sign_inService {
    public List<Sign_in> list();
    void signin(User user);

}
