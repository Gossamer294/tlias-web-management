package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理Controller
 */
@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 查询所有班级（用于下拉选择）
     */
    @GetMapping("/all")
    public Result findAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /**
     * 查询所有班级（用于列表展示）
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级列表");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    /**
     * 根据ID查询班级
     */
    @GetMapping("/get/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 根据ID查询班级（兼容前端路径）
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据ID查询班级: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 删除班级
     */
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        log.info("删除班级: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 删除班级（兼容旧路径）
     */
    @DeleteMapping("/delete")
    public Result deleteWithLegacyPath(@RequestParam Integer id){
        return delete(id);
    }

    /**
     * 分页查询班级
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询班级: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 修改班级
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}