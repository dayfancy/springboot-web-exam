package com.itheima.controller;

import com.itheima.common.Result;
import com.itheima.entity.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除部门: {}", id);
        deptService.removeById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}", dept);
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }


    /**
     * 修改部门
     */
    @PutMapping
    public Result update(Dept dept){
        log.info("修改部门:{}", dept);
        deptService.updateById(dept);
        return Result.success();
    }
}
