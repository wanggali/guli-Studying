package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Course;
import com.guli.edu.pojo.chapter.ChapterVo;
import com.guli.edu.pojo.vo.front.CourseFrontVo;
import com.guli.edu.pojo.vo.front.CourseWebVo;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseService;
import com.guli.utils.Result;
import com.guli.utils.user.CourseWebVoOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: gali
 * @Date: 2022-09-15 15:33
 * @Description:
 */
@RestController
@RequestMapping("edu/courseFront")
@CrossOrigin
public class CourseFrontController {
    @Resource
    private CourseService courseService;

    @Resource
    private ChapterService chapterService;
    /**
     * 条件查询带分页 查询课程
     */
    @PostMapping("/getFrontInfo/{page}/{limit}")
    public Result getFrontInfo(@PathVariable long page,
                               @PathVariable long limit,
                               @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<Course> coursePage = new Page<>(page, limit);
        Map<String,Object> map=courseService.getFrontInfo(coursePage,courseFrontVo);
        return Result.ok().data(map);
    }
    /**
     * 课程详情
     */
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId){
        //课程详情
        CourseWebVo courseWebVo=courseService.getBaseCourseInfo(courseId);
        //章节，小节
        List<ChapterVo> chapterVideo = chapterService.getChapterVideo(courseId);
        return Result.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideo);
    }
    /**
     * 根据课程id查询课程信息
     */
    @GetMapping("/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}
