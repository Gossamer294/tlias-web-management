package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询班级
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级
     */
    void save(Clazz clazz);

    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);

    /**
     * 删除班级
     */
    void deleteById(Integer id);

    /**
     * 查询所有班级（用于下拉选择）
     */
    List<Clazz> findAll();
}