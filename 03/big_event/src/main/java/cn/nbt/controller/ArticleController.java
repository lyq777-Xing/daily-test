package cn.nbt.controller;

import cn.nbt.pojo.Article;
import cn.nbt.pojo.PageBean;
import cn.nbt.pojo.Result;
import cn.nbt.service.ArticleService;
import cn.nbt.utils.JwtUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "添加文章")
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    @Operation(summary = "获取文章分页列表")
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }

}
