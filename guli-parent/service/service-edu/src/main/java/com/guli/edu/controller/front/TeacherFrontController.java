package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Course;
import com.guli.edu.pojo.Teacher;
import com.guli.edu.service.CourseService;
import com.guli.edu.service.TeacherService;
import com.guli.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: gali
 * @Date: 2022-09-15 14:28
 * @Description:
 */
@RestController
@RequestMapping("/edu/teacherFront")
@CrossOrigin
public class TeacherFrontController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;

    /**
     * 分页查询讲师
     */
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable long page,@PathVariable long limit){
        Page<Teacher> pageInfo = new Page<>(page,limit);
        Map<String,Object> map=teacherService.getTeacherFrontList(pageInfo);
        return Result.ok().data(map);
    }
    /**
     * 讲师详情
     */
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<Course> courseList = courseService.list(wrapper);
        return Result.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
