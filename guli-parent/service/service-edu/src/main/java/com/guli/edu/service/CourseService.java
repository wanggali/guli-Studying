package com.guli.edu.service;

import com.guli.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.pojo.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gali
 * @since 2022-09-04
 */
public interface CourseService extends IService<Course> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
