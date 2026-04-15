package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学员管理Controller
 */
@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询所有学生（用于列表展示）
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有学生列表");
        List<Student> studentList = studentService.findAll();
        return Result.success(studentList);
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/get/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询学生: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 根据ID查询学生（兼容前端路径）
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据ID查询学生: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 批量删除学生
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除学生: {}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 批量删除学生（兼容旧路径）
     */
    @DeleteMapping("/delete")
    public Result deleteWithLegacyPath(@RequestParam List<Integer> ids){
        return delete(ids);
    }

    /**
     * 分页查询学生
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询学生: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学生
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学生: {}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 修改学生
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生: {}", student);
        studentService.update(student);
        return Result.success();
    }
}