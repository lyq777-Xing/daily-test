package cn.nbt.service.impl;

import cn.nbt.mapper.ArticleMapper;
import cn.nbt.pojo.Article;
import cn.nbt.service.ArticleService;
import cn.nbt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author lyq
 * @time 2023/12/25 19:31
 */
@Service
public class IArticleService implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }
}
