package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学生
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     */
    void save(Student student);

    /**
     * 根据ID查询学生
     */
    Student getById(Integer id);

    /**
     * 修改学生
     */
    void update(Student student);

    /**
     * 批量删除学生
     */
    void delete(List<Integer> ids);

    /**
     * 查询所有学生
     */
    List<Student> findAll();
}