package cn.nbt.service.impl;

import cn.nbt.mapper.ArticleMapper;
import cn.nbt.pojo.Article;
import cn.nbt.pojo.PageBean;
import cn.nbt.service.ArticleService;
import cn.nbt.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
//        创建pageBean对象
        PageBean<Article> pageBean = new PageBean<>();
//        开启分页查询 借助PageHelper插件
        PageHelper.startPage(pageNum,pageSize);
//        调用mapper查询
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Article> as = articleMapper.list(id,categoryId,state);
//        page中提供了方法 获取PageHelper分页查询后，得到的总记录条数
        Page<Article> p = (Page<Article>) as;
//        将数据填充到PageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }
}
