package cn.nbt.service;

import cn.nbt.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void add(Category category);

    /**
     * 列表查询
     * @return
     */
    List<Category> list();

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category findById(Integer id);

    /**
     * 更新分类
     * @param category
     */
    void update(Category category);

    /**
     * 删除分类
     * @param id
     */
    void delete(Integer id);
}
