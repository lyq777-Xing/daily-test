package cn.nbt;

import org.junit.jupiter.api.Test;

/**
 * @author lyq
 * @time 2023/12/17 19:05
 */
public class ThreadLocalTest {
    @Test
    public void testThreadLocalGetAndSet(){
        ThreadLocal<Object> tl = new ThreadLocal<>();
        new Thread(() -> {
            tl.set("aaa");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"蓝色").start();

        new Thread(() -> {
            tl.set("bbb");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"粉色").start();
    }
}
