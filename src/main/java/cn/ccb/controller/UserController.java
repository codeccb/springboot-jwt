package cn.ccb.controller;

import cn.ccb.entity.User;
import cn.ccb.service.UserService;
import cn.ccb.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Map<String,Object> login(User user) {
        Map<String,Object> result = new HashMap<>();
        log.info("用户名: [{}]", user.getUsername());
        log.info("密码: [{}]", user.getPassword());

        try {
            User userDB = userService.login(user);
            Map<String, String> map = new HashMap<>();//用来存放payload
            map.put("id",userDB.getId());
            map.put("username", userDB.getUsername());
            String token = JWTUtils.getToken(map);
            result.put("state",true);
            result.put("msg","登录成功!!!");
            result.put("token",token); //成功返回token信息
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state","false");
            result.put("msg","用户名或密码错误！");
        }

        return result;
    }

    @GetMapping("/test/test")
    public Map<String, Object> test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "验证通过~~~");
        map.put("state", true);

        return map;
    }
}