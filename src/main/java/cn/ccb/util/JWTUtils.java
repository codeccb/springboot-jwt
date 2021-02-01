package cn.ccb.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * 封装jwt
 */
public class JWTUtils {

    private static final String SIGN="@@wgherds&8"; // 密钥

    /**
     * 生成token
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);

        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime()) // 指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN)); // 签名

        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);  // 验证
        return decodedJWT;
    }
}
