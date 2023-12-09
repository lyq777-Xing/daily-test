package cn.nbt.controller;

import cn.nbt.pojo.Result;
import cn.nbt.pojo.User;
import cn.nbt.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result register(@Pattern(regexp = "^\\S{5,16}&") String username, @Pattern(regexp = "^\\S{5,16}&") String password){
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
}
