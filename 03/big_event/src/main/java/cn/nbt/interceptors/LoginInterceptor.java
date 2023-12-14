package cn.nbt.interceptors;

import cn.nbt.pojo.Result;
import cn.nbt.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/14 14:01
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        令牌验证
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
//            放行
            return true;
        }catch (Exception e){
//            状态码为401
            response.setStatus(401);
            return false;
        }
    }
}
