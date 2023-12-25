package cn.nbt.controller;

import cn.nbt.pojo.Category;
import cn.nbt.pojo.Result;
import cn.nbt.service.CategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lyq
 * @time 2023/12/24 16:30
 */

@RestController
@RequestMapping("/category")
@Tag(name = "category")
@ApiSupport(author = "1661898579@qq.com",order = 3)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "添加文章分类")
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    @Operation(summary = "获取文章分类")
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }


    @Operation(summary = "根据id查询分类")
    @GetMapping("/detail")
    private Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @Operation(summary = "更新文章分类")
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @Operation(summary = "删除文章分类")
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
