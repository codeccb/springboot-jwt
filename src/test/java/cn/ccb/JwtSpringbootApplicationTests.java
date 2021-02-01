package cn.ccb;

import cn.ccb.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class JwtSpringbootApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id","001");
        map.put("username","ccb");

        String token = JWTUtils.getToken(map);
        System.out.println(token);

        DecodedJWT verify = JWTUtils.verify(token);
        System.out.println(verify.getClaim("id").asString());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getExpiresAt());
    }
}
