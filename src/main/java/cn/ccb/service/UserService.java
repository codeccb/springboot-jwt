package cn.ccb.service;

import cn.ccb.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(User user);//登录接口
}