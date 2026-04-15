package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1. 调用mapper接口, 获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); //map: pos=教研主管, num=1

        //2. 组装结果, 并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMapper.countStuDegreeData();
    }

    @Override
    public List<Map<String, Object>> getStuGenderData() {
        return studentMapper.countStuGenderData();
    }

    @Override
    public Map<String, Object> getStuCountData() {
        List<Map<String, Object>> list = studentMapper.countStuClazzData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("clazzList", clazzList);
        resultMap.put("dataList", dataList);
        // 兼容可能复用员工统计字段名的前端实现
        resultMap.put("jobList", clazzList);
        return resultMap;
    }
}
