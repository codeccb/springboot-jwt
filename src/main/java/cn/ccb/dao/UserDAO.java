package cn.ccb.dao;

import cn.ccb.entity.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserDAO {
    User login(User user);
}
