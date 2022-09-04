package com.guli.edu.controller;


import com.guli.edu.pojo.vo.CourseInfoVo;
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
    @PostMapping("addCourseInfo")
    @ApiOperation(value = "添加课程")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.saveCourseInfo(courseInfoVo);
        return Result.ok();
    }
}

