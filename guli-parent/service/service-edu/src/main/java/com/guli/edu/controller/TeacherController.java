package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Teacher;
import com.guli.edu.pojo.vo.TeacherQuery;
import com.guli.edu.service.TeacherService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-01
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    /**
     * 查询讲师的所有数据
     */
    @ApiOperation(value = "查询讲师的所有数据")
    @GetMapping("/findAll")
    public Result findAll(){
        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }
    /**
     * 逻辑删除讲师
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return Result.ok();
        }
        return Result.error();
    }
    /**
     * 分页查询讲师
     * current:当前页
     * limit:每一页的条数
     */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,
                                  @PathVariable long limit){
        Page<Teacher> page= new Page<>(current,limit);
        teacherService.page(page, null);
        //page.getRecords() 获取所有的集合
        //page.getTotal() 获取总条数
        return Result.ok().data("total",page.getTotal()).data("rows",page.getRecords());
    }
    /**
     * 条件查询带分页
     * @RequestBody 利用json传输数据
     */
    @ApiOperation(value = "条件查询带分页")
    @GetMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> page = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空，拼接条件
        if (!StringUtils.isEmpty(level)) {
            // eq 相等
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(name)) {
            //like 模糊查询
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(begin)) {
            //ge 大于等于
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            //le 小于等于
            wrapper.le("gmt_create", end);
        }
        wrapper.orderByDesc("gmt_create");
        teacherService.page(page,wrapper);
        return Result.ok().data("total",page.getTotal()).data("rows",page.getRecords());
    }
    @ApiOperation("添加老师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.save(teacher);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 根据id查询讲师
     * @param id
     * @return
     */
    @ApiOperation(value ="id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }
    /**
     * 讲师修改
     */
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

