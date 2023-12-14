package cn.nbt.controller;

import cn.nbt.pojo.Result;
import cn.nbt.pojo.User;
import cn.nbt.service.UserService;
import cn.nbt.utils.JwtUtil;
import cn.nbt.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/9 14:58
 */

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
//        Spring Validation 能够对注册接口参数 进行合法校验 不需要利用if-else语句 增加了代码的可读性 注：使用这个之后需要处理异常
//        查询用户
            User user = userService.findByName(username);
//        判断用户名是否已经被占用
            if(user == null){
//           注册用户
                userService.register(username,password);
                return Result.success();
            }else {
                return Result.error("用户名已被占用");
            }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
//        根据用户名查询用户
        User user = userService.findByName(username);
//        判断该用户是否存在
        if(user == null){
            return Result.error("用户名错误");
        }
//        判断密码是否正确
        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
}
