package cn.nbt.controller;

import cn.nbt.pojo.Result;
import cn.nbt.utils.JwtUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/9 20:35
 */
@RestController
@RequestMapping("/article")
@Tag(name = "ArticleController")
@ApiSupport(author = "1661898579@qq.com",order = 2)
public class ArticleController {
    @Operation(summary = "list")
    @GetMapping("/list")
    public Result<String> list(){

        return Result.success("所有的文章数据");
    }
}
