package cn.nbt.interceptors;

import cn.nbt.pojo.Result;
import cn.nbt.utils.JwtUtil;
import cn.nbt.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/14 14:01
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        令牌验证
        String token = request.getHeader("Authorization");
        try {
//            从redis中获取相同的token
            ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
            String redisToken = stringStringValueOperations.get(token);
            if(redisToken == null){
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
//            将业务数据存储到线程中
            ThreadLocalUtil.set(claims);
//            放行
            return true;
        }catch (Exception e){
//            状态码为401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
