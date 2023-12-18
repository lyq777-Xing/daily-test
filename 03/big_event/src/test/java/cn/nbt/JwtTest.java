package cn.nbt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/14 13:16
 */
public class JwtTest {
    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
//        生成jwt的代码
        String token = JWT.create()
//                添加载荷
                .withClaim("user", claims)
//                添加过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
//                指定算法配置密钥
                .sign(Algorithm.HMAC256("nbtcn"));
        System.out.println(token);
    }

    @Test
    public void testParse(){
//        定义字符串，模拟token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDI1NzQ1NDF9." +
                "NyZaFlU6eh6l0w3GHnFxZr9xytfmvKR6NJ_EqUCTh3o";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("nbtcn")).build();
//        验证token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim user = claims.get("user");
        System.out.println(user);
    }

}
