package cn.nbt.service.impl;

import cn.nbt.mapper.UserMapper;
import cn.nbt.pojo.User;
import cn.nbt.service.UserService;
import cn.nbt.utils.Md5Util;
import cn.nbt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/9 14:59
 */
@Service
public class IUserService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String username) {
        User user = userMapper.findByName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
//        密码加密
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setCreateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
