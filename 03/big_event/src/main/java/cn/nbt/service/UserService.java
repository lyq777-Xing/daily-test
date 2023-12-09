package cn.nbt.service;

import cn.nbt.pojo.User;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);
}
