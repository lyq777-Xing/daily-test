package cn.nbt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lyq
 * @time 2023/12/5 21:29
 * 启动类
 */
@SpringBootApplication
@Slf4j
public class bigEventApplication {
    public static void main(String[] args) {
        System.out.println("hello~");
        SpringApplication.run(bigEventApplication.class,args);
    }
}
