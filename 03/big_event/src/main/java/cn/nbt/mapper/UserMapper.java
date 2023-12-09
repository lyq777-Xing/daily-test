package cn.nbt.mapper;

import cn.nbt.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lyq
 * @time 2023/12/9 14:59
 */

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询数据
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User findByName(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username}, #{password}, now(),now())")
    void add(String username, String password);
}
