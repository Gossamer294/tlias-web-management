package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 计算起始位置
        int start = (clazzQueryParam.getPage() - 1) * clazzQueryParam.getPageSize();
        clazzQueryParam.setStart(start);

        // 查询班级数据
        List<Clazz> clazzList = clazzMapper.page(clazzQueryParam);

        // 查询班级总数
        Integer count = clazzMapper.count(clazzQueryParam);

        // 构建分页结果
        return new PageResult<>(count.longValue(), clazzList);
    }

    @Override
    public void save(Clazz clazz) {
        // 补全基础属性 - createTime, updateTime
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 调用Mapper接口方法插入数据
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        // 补全基础属性 - updateTime
        clazz.setUpdateTime(LocalDateTime.now());

        // 调用Mapper接口方法更新班级
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}