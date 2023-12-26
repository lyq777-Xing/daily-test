package cn.nbt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author lyq
 * @time 2023/12/26 20:09
 */

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
//        在redis中存储一个键值对
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("username","lyq");
//        设置过期日期
        stringStringValueOperations.set("id","N001",15, TimeUnit.MINUTES);
    }

    @Test
    public void testGet(){
//        在redis中存储一个键值对
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        stringStringValueOperations.get("username");
    }
}
