package cn.nbt.controller;

import cn.nbt.pojo.Result;
import cn.nbt.utils.AliOssUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lyq
 * @time 2023/12/26 18:39
 */
@RestController
@Tag(name = "文件上传")
@ApiSupport(author = "1661898579@qq.com",order = 4)
public class FileUploadController {

    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
//        保证名字的唯一性 防止文件覆盖
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("E:\\实验室\\demo\\03\\big_event\\files\\" + fileName));
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }
}
