package cn.ccb.interceptor;

import cn.ccb.util.JWTUtils;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 配置拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        HashMap<String, Object> map = new HashMap<>();

        try {
            JWTUtils.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            map.put("state",false);
            map.put("msg","Token已过期");
            e.printStackTrace();
        }catch (SignatureGenerationException e){
            map.put("state",false);
            map.put("msg","签名错误！");
        }catch (AlgorithmMismatchException e){
            map.put("state",false);
            map.put("msg","加密算法不匹配！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","无效token！");
        }

        JSON json = JSONUtil.parse(map);
        response.setContentType("application/json;utf-8");
        response.getWriter().println(json);
        return false;
    }
}
