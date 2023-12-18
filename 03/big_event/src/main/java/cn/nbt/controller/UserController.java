package cn.nbt.controller;

import cn.nbt.pojo.Result;
import cn.nbt.pojo.User;
import cn.nbt.service.UserService;
import cn.nbt.utils.JwtUtil;
import cn.nbt.utils.Md5Util;
import cn.nbt.utils.ThreadLocalUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/9 14:58
 */

@RestController
@RequestMapping("/user")
@Validated
@Tag(name = "UserController")
@ApiSupport(author = "1661898579@qq.com",order = 1)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "register")
    @Parameters({
            @Parameter(name = "username",description = "用户名",required = true),
            @Parameter(name = "password",description = "密码",required = true)
    })
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

    @Operation(summary = "login")
    @Parameters({
            @Parameter(name = "username",description = "用户名",required = true),
            @Parameter(name = "password",description = "密码",required = true)
    })
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

    @Operation(summary = "userInfo")
    @Parameters({
            @Parameter(name = "token",description = "token",required = true),
    })
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//        根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByName(username);
        return Result.success(user);
    }

    @Operation(summary = "update")
    @Parameters({
            @Parameter(name = "user",description = "用户对象",required = true),
    })
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @Operation(summary = "updateAvatar")
    @Parameters({
            @Parameter(name = "avatarUrl",description = "图片url",required = true),
    })
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @Operation(summary = "updatePad")
    @Parameters({
            @Parameter(name = "params",description = "密码",required = true),
    })
    @PatchMapping("/updatePad")
    public Result updatePad(@RequestBody Map<String,String> params){
//        校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) ||
            !StringUtils.hasLength(newPwd) ||
            !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
//        校验原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }
//        校验new_pwd和re_pwd是否一样
        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写的更新密码不一致");
        }
        userService.updatePwd(newPwd);
        return Result.success();
    }

}
