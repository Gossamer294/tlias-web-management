package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别人数
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学员学历人数
     */
    @GetMapping("/stuDegreeData")
    public Result getStuDegreeData(){
        log.info("统计学员学历人数");
        List<Map<String, Object>> degreeList = reportService.getStuDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 统计学员学历人数（兼容前端接口名）
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历人数(兼容接口)");
        List<Map<String, Object>> degreeList = reportService.getStuDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 统计学员性别人数
     */
    @GetMapping("/stuGenderData")
    public Result getStuGenderData(){
        log.info("统计学员性别人数");
        List<Map<String, Object>> genderList = reportService.getStuGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计学员人数（兼容前端接口名，返回学员性别统计）
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计学员人数(兼容接口)");
        Map<String, Object> stuCountData = reportService.getStuCountData();
        return Result.success(stuCountData);
    }
}
