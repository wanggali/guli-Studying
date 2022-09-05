package com.guli.edu.controller;


import com.guli.edu.pojo.vo.CourseInfoVo;
import com.guli.edu.pojo.vo.CoursePublishVo;
import com.guli.edu.service.CourseService;
import com.guli.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}

