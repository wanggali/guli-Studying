package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.pojo.Course;
import com.guli.edu.pojo.Teacher;
import com.guli.edu.service.CourseService;
import com.guli.edu.service.TeacherService;
import com.guli.utils.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: gali
 * @Date: 2022-09-08 20:44
 * @Description:
 */
@RestController
@RequestMapping("/edu/indexFront")
@CrossOrigin
public class IndexFrontController {
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;

    /**
     * 查询前8条热门课程,前4热门讲师
     */
    @GetMapping("/index")
    public Result index(){
        List<Course> listCourse = courseService.findIndexHotCourse();
        List<Teacher> listTeacher = teacherService.findIndexHotTeacher();
        return Result.ok().data("eduList",listCourse).data("teacherList",listTeacher);
    }
}
