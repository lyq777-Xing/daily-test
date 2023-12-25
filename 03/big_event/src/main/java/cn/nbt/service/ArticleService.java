package cn.nbt.service;

import cn.nbt.pojo.Article;
import cn.nbt.pojo.PageBean;

public interface ArticleService {

    /**
     * 添加文章
     * @param article
     */
    void add(Article article);

    /**
     * 条件分页列表查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
