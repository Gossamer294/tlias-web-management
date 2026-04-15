package com.itheima.service.impl;

import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 计算起始位置
        int start = (studentQueryParam.getPage() - 1) * studentQueryParam.getPageSize();
        studentQueryParam.setStart(start);

        // 查询学生数据
        List<Student> studentList = studentMapper.page(studentQueryParam);

        // 查询学生总数
        Integer count = studentMapper.count(studentQueryParam);

        // 构建分页结果
        return new PageResult<>(count.longValue(), studentList);
    }

    @Override
    public void save(Student student) {
        // 补全基础属性 - createTime, updateTime
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        // 调用Mapper接口方法插入数据
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        // 补全基础属性 - updateTime
        student.setUpdateTime(LocalDateTime.now());

        // 调用Mapper接口方法更新学生
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}