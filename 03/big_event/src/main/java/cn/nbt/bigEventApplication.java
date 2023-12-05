package cn.nbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lyq
 * @time 2023/12/5 21:29
 * 启动类
 */
@SpringBootApplication
public class bigEventApplication {
    public static void main(String[] args) {
        System.out.println("hello~");
        SpringApplication.run(bigEventApplication.class,args);
    }
}
