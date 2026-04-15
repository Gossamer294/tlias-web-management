package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 查询所有部门（用于列表展示）
     */
    @GetMapping("/list")
    public Result listAll(){
        log.info("查询所有部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 - 省略@RequestParam (前端传递的请求参数名与服务端方法形参名一致) [推荐]
     */
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("根据ID删除部门: " + id);
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门: " + dept);
        log.info("新增部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/get/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门 : " + id);
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 根据ID查询部门（兼容前端路径）
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }


    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门: " + dept);
        log.info("修改部门:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
