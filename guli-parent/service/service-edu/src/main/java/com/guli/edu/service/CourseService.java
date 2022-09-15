package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.pojo.vo.CourseInfoVo;
import com.guli.edu.pojo.vo.CoursePublishVo;
import com.guli.edu.pojo.vo.front.CourseFrontVo;
import com.guli.edu.pojo.vo.front.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String courseId);

    List<Course> findIndexHotCourse();

    Map<String, Object> getFrontInfo(Page<Course> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
