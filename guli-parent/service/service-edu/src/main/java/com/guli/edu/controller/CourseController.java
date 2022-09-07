package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Course;
import com.guli.edu.pojo.vo.CourseInfoVo;
import com.guli.edu.pojo.vo.CoursePublishVo;
import com.guli.edu.pojo.vo.CourseQuery;
import com.guli.edu.service.CourseService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
@Api(description = "课程管理")
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
     * 添加课程基本信息
     */
    @PostMapping("/addCourseInfo")
    @ApiOperation(value = "添加课程")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id =courseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }
    /**
     * 根据课程id查询课程基本信息
     */
    @GetMapping("/getCourseInfo/{courseId}")
    @ApiOperation(value = "查询课程基本信息")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }
    /**
     * 修改课程信息
     */
    @PostMapping("/updateCourseInfo")
    @ApiOperation(value = "修改课程信息")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }
    /**
     * 根据课程id查询课程确认信息
     */
    @GetMapping("/getPublishCourseInfo/{id}")
    @ApiOperation(value = "课程id查询课程确认信息")
    public Result getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo=courseService.getPublishCourseInfo(id);
        return Result.ok().data("publishCourse",coursePublishVo);
    }
    /**
     * 课程最终发布
     */
    @PostMapping("/publishCourse/{id}")
    @ApiOperation(value = "课程最终发布")
    public Result publishCourse(@PathVariable String id){
        Course course = new Course();
        course.setId(id).setStatus("Normal");
        courseService.updateById(course);
        return Result.ok();
    }
    /**
     * 课程列表
     */
    @GetMapping
    @ApiOperation(value = "课程列表")
    public Result getCourseList(){
        List<Course> list = courseService.list(null);
        return Result.ok().data("list",list);
    }

    /**
     * 根据id删除课程
     */
    @DeleteMapping("/{courseId}")
    public Result removeCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Result.ok();
    }
    //4.条件查询分页方法
    @ApiOperation(value = "条件查询分页方法")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable Long current,
                                 @PathVariable Long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery) {
        //创建page
        Page<Course> pageCondition = new Page<>(current, limit);

        //QueryWrapper,构建
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //多条件组合查询，动态sql
        String status = courseQuery.getStatus();
        String title = courseQuery.getTitle();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        wrapper.orderByDesc("gmt_create");

        //调用方法，实现分页查询
        IPage<Course> page = courseService.page(pageCondition, wrapper);

        long total = page.getTotal();//获取总记录数
        List<Course> records = page.getRecords();//获取分页后的list集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return Result.ok().data(map);
    }
}

