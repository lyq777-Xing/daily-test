package cn.nbt.mapper;

import cn.nbt.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article
     */
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    /**
     * 分页列表
     * @param id
     * @param categoryId
     * @param state
     * @return
     */
    List<Article> list(Integer id, Integer categoryId, String state);
}
